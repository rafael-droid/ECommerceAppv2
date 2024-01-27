package pl.project.ecommerceapp.Fragment.Shopping

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import pl.project.ecommerceapp.Adapter.ProductsAdapter
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Data.Products
import pl.project.ecommerceapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import pl.project.ecommerceapp.databinding.FragmentHomeBinding


class HomeFragment: Fragment(){
        private lateinit var binding: FragmentHomeBinding
        private  var thiscontext: Context? = null;



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        if (container != null) {
            thiscontext = container.getContext()
        }
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitClient.apiService.getProducts()
        call.enqueue(object : Callback<Products> {

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if(response.isSuccessful){
                    val productsResponse = response.body()
                    if (productsResponse != null) {
                        var products = productsResponse.items
                        binding.progress.visibility = View.GONE
                        val adapter = ProductsAdapter(products)
                        binding.rvProducts.layoutManager = LinearLayoutManager(thiscontext)
                        binding.rvProducts.adapter = adapter



                        adapter.setOnClickListener { position, model ->
                            Log.d("Adapter","kliknięcie $position")
                            val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
                            if (fragmentManager != null) {
                                Log.d("Home", model.id.toString())
                                val fragment = ProductDetailFragment.newInstance(model.id)
                                fragmentManager.replace(R.id.container, fragment)
                                fragmentManager.addToBackStack(null)
                                fragmentManager.commit()


                            };
                            };

                        }
                    }

                }


            override fun onFailure(call: Call<Products>, t: Throwable) {
                Log.d("flag: ", t.message.toString())

            }
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


}