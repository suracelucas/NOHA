package ar.unlam.nohaapp.domain

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat.startActivity
import ar.unlam.nohaapp.ui.view.MainActivity
import ar.unlam.nohaapp.ui.view.MenuActivity
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
const val RESULTADO = "ar.unlam.nohaapp.RESULTADO_DE_ESCANEO"
class QRCodeAnalyzer(private val context : MainActivity) : ImageAnalysis.Analyzer{
    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        val options = BarcodeScannerOptions.Builder().setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        ).build()
        // Corrige el angulo de la imagen y la obtiene
        image.image?.run {
            val image = InputImage.fromMediaImage(this, image.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            val result = scanner.process(image).addOnSuccessListener {
                barcodes ->
                for(barcode in barcodes){
                    val bound = barcode.boundingBox
                    val corners = barcode.cornerPoints
                    val rawValue = barcode.rawValue
                    Log.i("BARCODE_SCANNER", "El contenido es: $rawValue")
                    Toast.makeText(context, "El contenido es: $rawValue", Toast.LENGTH_LONG).show()
                    when(barcode.valueType){
                        Barcode.TYPE_TEXT ->{
                            val text = barcode.displayValue!!
                        }
                        Barcode.TYPE_URL -> {
                            val title = barcode.url!!.title
                            val url = barcode.url!!.url
                        }
                    }
                    val intent = Intent(context, MenuActivity::class.java).apply {
                        putExtra(RESULTADO, rawValue)
                    }
                    startActivity(context, intent, null)
                }

            }
                .addOnFailureListener {
                    Log.e("BARCODE_SCANNER", it.message, it)
                }
        }

    }

}