package ar.unlam.nohaapp.ui.view

import android.content.Intent
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
import ar.unlam.nohaapp.domain.RESULTADO
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.jar.Manifest

class CameraFragment : Fragment() {
    private lateinit var cameraBinding: FragmentCameraBinding
    // <- Inicio, Camera
    private lateinit var cameraExecutor: ExecutorService
    // -> Fin, Camera
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraBinding = FragmentCameraBinding.inflate(LayoutInflater.from(requireContext()))
        startCamera()
        // Crea el Thread para la camara */
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    // <- Inicio, funciones de camara
    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
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
                .also { imageAnalysis -> imageAnalysis.setAnalyzer(cameraExecutor, QRCodeAnalyzer{codigoQr(it)})}
            try{
                // Asegurar que nada esta unido al cameraProvider
                cameraProvider.unbindAll()

                // Casos de uso, unir vistas
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, barcodeAnalyzer)

            } catch (exc: Exception){
                Log.e(TAG, "No se pueden unir las vistas", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    // -> Fin, funciones de camara

    // <- Inicio, variables de camara
    companion object {
        private const val TAG = "CameraXBasic"
    }
    // -> Fin, variables de camara

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return cameraBinding.root
    }

    @Synchronized
    fun codigoQr(rawValue : String){
        if(rawValue.subSequence(0, 6).equals("HAB - ")){
            cameraExecutor.shutdown()
            var habitacion = rawValue.subSequence(6, rawValue.length)
            var intent = Intent(requireContext(), MenuActivity::class.java).apply {
                putExtra(RESULTADO, habitacion)
            }
            startActivity(intent)
            requireActivity().finish()
        }
    }
}