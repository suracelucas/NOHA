package ar.unlam.nohaapp.domain

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

const val RESULTADO = "ar.unlam.nohaapp.RESULTADO_DE_ESCANEO"
class QRCodeAnalyzer(val codigoQr:(String)-> Unit) : ImageAnalysis.Analyzer{
    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        BarcodeScannerOptions.Builder().setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        ).build()
        // Corrige el angulo de la imagen y la obtiene
        imageProxy.image?.run {
            val image = InputImage.fromMediaImage(this, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            scanner.process(image).addOnSuccessListener {
                barcodes ->

                for(barcode in barcodes){
                    val rawValue = barcode.rawValue
                    Log.i("BARCODE_SCANNER", "El contenido es: $rawValue")
                    codigoQr(rawValue)
                }
            }
                .addOnFailureListener {
                    Log.e("BARCODE_SCANNER", it.message, it)
                }
                .addOnCompleteListener{
                    imageProxy.close()
                }
        }
    }
}


