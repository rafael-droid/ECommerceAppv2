package pl.project.ecommerceapp.Fragment.Shopping


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager
import pl.project.ecommerceapp.Data.Address


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
        val call = RetrofitClient.apiService.getAddress(token= "${sessionManager.fetchAuthToken()}")

        call.enqueue(object : Callback<Address> {
            override fun onResponse(call: Call<Address>, response: Response<Address>) {
                if (response.isSuccessful) {
                    val addressResponse = response.body()
                    if (addressResponse != null) {
                        binding.tvFirstName.text = addressResponse.items.first().user.firstName
                        binding.tvLastName.text = addressResponse.items.first().user.lastName
                        binding.tvEmail.text = addressResponse.items.first().user.email
                        binding.tvCountry.text = addressResponse.items.first().country
                        binding.tvRegion.text = addressResponse.items.first().region
                        binding.tvCity.text = addressResponse.items.first().city
                        binding.tvZipCode.text = addressResponse.items.first().zipCode
                        binding.tvStreetAddress.text = addressResponse.items.first().streetAddress

                    }

                }
            }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }

}

