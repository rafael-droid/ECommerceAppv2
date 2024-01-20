package pl.project.ecommerceapp.Fragment.Shopping

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import pl.project.ecommerceapp.Adapter.ProductsAdapter
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Data.Products
import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.FragmentHomeBinding
import pl.project.ecommerceapp.databinding.FragmentScanBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScanFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: FragmentScanBinding
    private  var thiscontext: Context? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentScanBinding.inflate(inflater, container, false)
        if (container != null) {
            thiscontext = container.getContext()
        };
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitClient.apiService.getProducts()


        codeScanner = thiscontext?.let { CodeScanner(it, binding.scannerView) }!!

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not



        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}