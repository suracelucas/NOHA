package ar.unlam.nohaapp.ui.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.FragmentCameraBinding
import ar.unlam.nohaapp.domain.QRCodeAnalyzer
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.jar.Manifest

typealias LumaListener = (luma : Double) -> Unit
class CameraFragment(private val context : MainActivity) : Fragment() {
    private lateinit var cameraBinding: FragmentCameraBinding
    // <- Inicio, Camera
    private lateinit var cameraExecutor: ExecutorService
    // -> Fin, Camera
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraBinding = FragmentCameraBinding.inflate(LayoutInflater.from(context))

// Se piden los permisos de la camara
        if(allPermissionsGranted()){
            startCamera()
        } else {
            ActivityCompat.requestPermissions(context,
                CameraFragment.REQUIRED_PERMISSIONS,
                CameraFragment.REQUEST_CODE_PERMISSIONS
            )
        }

        // Crea el Thread para la camara
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    // <- Inicio, funciones de camara
    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        // Con el Runnable, el Executor corre en el Thread principal
        cameraProviderFuture.addListener(Runnable {
            // Une el lifecycle de la camara al lifecycle del de la vista
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            //preview
            val preview = Preview.Builder().build().also { it.setSurfaceProvider(cameraBinding.viewFinder.surfaceProvider) }
            // Se selecciona la camara trasera como default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val barcodeAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { imageAnalysis -> imageAnalysis.setAnalyzer(cameraExecutor, QRCodeAnalyzer(context)) }
            try{
                // Asegurar que nada esta unido al cameraProvider
                cameraProvider.unbindAll()

                // Casos de uso, unir vistas
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, barcodeAnalyzer)
            } catch (exc: Exception){
                Log.e(TAG, "No se pueden unir las vistas", exc)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all{
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(context,
                    "Permisos no otorgados por el usuario.",
                    Toast.LENGTH_SHORT).show()
                //finish()
            }
        }
    }
    // -> Fin, funciones de camara

    // <- Inicio, variables de camara
    companion object {
        private const val TAG = "CameraXBasic"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
    }
    // -> Fin, variables de camara

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return cameraBinding.root
    }
}