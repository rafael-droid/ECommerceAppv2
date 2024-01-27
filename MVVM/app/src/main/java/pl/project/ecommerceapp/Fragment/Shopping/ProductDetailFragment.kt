package pl.project.ecommerceapp.Fragment.Shopping


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import pl.project.ecommerceapp.Api.Constants.BASE_URL
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager
import pl.project.ecommerceapp.Data.Cart
import pl.project.ecommerceapp.Data.CartResponse
import pl.project.ecommerceapp.Data.ItemDetail
import pl.project.ecommerceapp.databinding.FragmentProductDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailFragment() : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private var productId: Int = 0
    private lateinit var sessionManager: SessionManager
    private lateinit var thiscontext: Context
    private var state: Boolean = false;

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProductDetailBinding.inflate(inflater, container, false)
        productId = arguments?.getInt(ARG_POSITION, 0) ?: 0
        if (container != null) {
            thiscontext = container.getContext()
        }
        sessionManager = SessionManager(requireContext())
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call = RetrofitClient.apiService.getProductById(productId)
        call.enqueue(object : Callback<ItemDetail> {
            override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                if (response.isSuccessful) {
                    var products = response.body()
                    if (products != null) {
                        binding.tvProductsName.text = products.name
                        binding.tvProductsCategory.text = products.category.name
                        binding.tvProductsPrice.text = products.price.toString()
                        val url = BASE_URL +  products.imageUrls.first().url
                        Log.d("IMG",url)
                        Glide.with(thiscontext).load(url).into(binding.imgDetail)
                    }

                }
            }

            override fun onFailure(call: Call<ItemDetail>, t: Throwable) {
                Log.d("flag: ", t.message.toString())

            }
        })




        val amountText = binding.edAmount.text.toString()
        val amount: Int? = amountText.toIntOrNull()
        val addProduct = Cart(productId = productId.toString(), quantity = 1)


        binding.buttonAddProduct.setOnClickListener{
            if (binding.edAmount.text.isNullOrEmpty()){
                Toast.makeText(thiscontext, "Enter quantity", Toast.LENGTH_LONG)
                    .show()

            }else
                state = true

            if(state) {
                val callAdd = RetrofitClient.apiService.addProduct(
                    cart = addProduct,
                    token = "${sessionManager.fetchAuthToken()}"
                )

                callAdd.enqueue(object : Callback<CartResponse> {
                    override fun onResponse(
                        callAdd: Call<CartResponse>,
                        responseAdd: Response<CartResponse>
                    ) {
                        if (responseAdd.isSuccessful) {
                            Toast.makeText(thiscontext, "Product was added", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                        Log.d("adderror ", t.message.toString())

                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView() }


}


