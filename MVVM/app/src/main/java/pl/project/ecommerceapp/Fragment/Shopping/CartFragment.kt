package pl.project.ecommerceapp.Fragment.Shopping


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.project.ecommerceapp.Adapter.ProductsInCartAdapter
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager
import pl.project.ecommerceapp.Data.CartListResponse
import pl.project.ecommerceapp.Data.CouponResponse
import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.FragmentCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var sessionManager: SessionManager
    private  var thiscontext: Context? = null;
    private var coupon:String = ""
    private var status: Boolean = false
    private var discount: Double = 0.0
    private var price: Double = 0.0

    companion object {
        private const val ARG_COUPON = "coupon"

        fun newInstance(coupon: String): CartFragment {
            val fragment = CartFragment()
            val args = Bundle()
            args.putString(ARG_COUPON, coupon)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentCartBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())
        coupon = (arguments?.getString(ARG_COUPON, "") ?: "")
        if (container != null) {
            thiscontext = container.getContext()
        }
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitClient.apiService.getCart(token= "${sessionManager.fetchAuthToken()}")
        Log.d("kod",coupon)

        call.enqueue(object : Callback<CartListResponse> {
            override fun onResponse(call: Call<CartListResponse>, response: Response<CartListResponse>) {
                if (response.isSuccessful) {
                    val productsResponse = response.body()
                    if (productsResponse != null) {
                        val adapter = ProductsInCartAdapter(productsResponse.items)
                        binding.rvCart.layoutManager = LinearLayoutManager(thiscontext)
                        binding.rvCart.adapter = adapter

                        price = countPrice(productsResponse)

                        Log.d("Price","first: $price")

                        if (!coupon.isNullOrEmpty()) {
                            val callCoupon = RetrofitClient.apiService.checkCoupon(
                                token = "${sessionManager.fetchAuthToken()}",
                                code = coupon
                            )
                            callCoupon.enqueue(object : Callback<CouponResponse> {
                                override fun onResponse(
                                    call: Call<CouponResponse>,
                                    response: Response<CouponResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        val couponResponse = response.body()
                                        if (couponResponse != null) {
                                            if (couponResponse.isValid) {
                                                discount = couponResponse.percentage.toDouble()
                                                val previousPrice = price
                                                price *= (1 - (discount / 100))

                                                this@CartFragment.price = price
                                                binding.tvDiscount.visibility = View.VISIBLE
                                                binding.tvDiscount.text = "Discount: $discount %"
                                                binding.tvTotalPrice.text = price.toString()

                                                Log.d("Price","first: $price")
                                                binding.tvTotalPrice2.visibility = View.VISIBLE
                                                binding.tvTotalPrice2.text = previousPrice.toString()
                                                binding.tvPreviousPrice.visibility = View.VISIBLE
                                            }

                                        }
                                    }
                                }

                                override fun onFailure(call: Call<CouponResponse>, t: Throwable) {
                                    Log.d("Error", t.message.toString())
                                }
                            })


                        }




                    }

                }
            }

            override fun onFailure(call: Call<CartListResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })



        binding.imgScan.setOnClickListener{
            status = true
            val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
            if (fragmentManager != null) {
                val fragment = ScanFragment()
                fragmentManager.replace(R.id.container, fragment)
                fragmentManager.addToBackStack(null)
                fragmentManager.commit()

            };
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    private  fun countPrice(productsResponse: CartListResponse): Double {
        for( i in productsResponse.items){
            price += (i.quantity * i.product.price).toDouble()
            Log.d("Price", price.toString())
        }

        binding.tvTotalPrice.text = (price).toString()
        Log.d("Price", price.toString())
        return price
    }
    fun checkCoupon(price: Double): Double {

        return price
    }

}