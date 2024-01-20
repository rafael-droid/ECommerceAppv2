package pl.project.ecommerceapp.Fragment.Shopping


import android.content.Context
import pl.project.ecommerceapp.Data.Adrress
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager


import pl.project.ecommerceapp.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private  var thiscontext: Context? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProfileBinding.inflate(inflater, container, false)
        if (container != null) {
            thiscontext = container.getContext()
        };
        sessionManager = SessionManager(requireContext())
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitClient.apiService.getAddress(token = "Bearer ${sessionManager.fetchAuthToken()}")

        call.enqueue(object : Callback<Adrress> {
            override fun onResponse(call: Call<Adrress>, response: Response<Adrress>) {
                if (response.isSuccessful) {
                    val addressResponse = response.body()
                    if (addressResponse != null) {
                        binding.tvCountry.text = addressResponse.country
                        binding.tvRegion.text = addressResponse.region
                        binding.tvCity.text = addressResponse.city
                        binding.tvZipCode.text = addressResponse.zipCode
                        binding.tvStreetAddress.text = addressResponse.streetAddress

                    }

                }
            }

            override fun onFailure(call: Call<Adrress>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }

}

