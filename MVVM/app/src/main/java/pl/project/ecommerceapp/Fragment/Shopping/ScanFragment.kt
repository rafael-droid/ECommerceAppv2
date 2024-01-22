package pl.project.ecommerceapp.Fragment.Shopping

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import pl.project.ecommerceapp.databinding.FragmentScanBinding



class ScanFragment : Fragment() {
    private lateinit var binding: FragmentScanBinding
    private  var thiscontext: Context? = null;
    private val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted: Boolean ->
                if (isGranted){

                }
                else{

                }
            }

    private val scanLauncher = registerForActivityResult(ScanContract()){
        result: ScanIntentResult ->
        run {
            if(result.contents == null){
                Toast.makeText(context,"Cancelled", Toast.LENGTH_SHORT).show()
            }else{
                setResult(result.contents)
            }
    }
    }

    private fun setResult(string: String) {
            binding.textResult.text = string
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentScanBinding.inflate(inflater, container, false)
        if (container != null) {
            thiscontext = container.getContext()
        };
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fab.setOnClickListener{
            context?.let { it1 -> checkPermissionCamera(it1) }
        }


    }

    private fun checkPermissionCamera(context: Context) {
        if(ContextCompat.checkSelfPermission(context,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            showCamera()
        }else if(shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
            Toast.makeText(context,"Camera permission required", Toast.LENGTH_SHORT).show()
        }else{
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    private fun showCamera(){
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)
        scanLauncher.launch(options)

    }
}