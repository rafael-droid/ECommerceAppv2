package pl.project.ecommerceapp.Fragment.LoginRegister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import pl.project.ecommerceapp.Activity.ShoppingActivity
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Data.LoginRequest
import pl.project.ecommerceapp.Data.LoginResponse
import pl.project.ecommerceapp.Data.RegisterRequest
import pl.project.ecommerceapp.Data.RegisterResponse
import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.ActivityLoginBinding
import pl.project.ecommerceapp.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var check: Boolean = false

        binding.edFirstnameRegister.addTextChangedListener{
            if(binding.edFirstnameRegister.length()<3){
                binding.tvErrorFirstName.visibility = View.VISIBLE
                check = false
            }else{
                binding.tvErrorFirstName.visibility = View.GONE
                check = true
            }
        }

        binding.edLastnameRegister.addTextChangedListener{
            if(binding.edLastnameRegister.length()<3){
                binding.tvErrorLastname.visibility = View.VISIBLE
                check = false
            }else{
                binding.tvErrorLastname.visibility = View.GONE
                check = true
            }
        }

        binding.edEmailRegister.addTextChangedListener{
            if(binding.edEmailRegister.length()<5 || !binding.edEmailRegister.text.toString().contains("@")){
                binding.tvErrorEmail.visibility = View.VISIBLE
                check = false
            }else{
                binding.tvErrorEmail.visibility = View.GONE
                check = true
            }
        }

        binding.edPasswordRegister.addTextChangedListener{
            if(binding.edPasswordRegister.length()<4){
                binding.tvErrorPassword.visibility = View.VISIBLE
                check = false
            }else{
                binding.tvErrorPassword.visibility = View.GONE
                check = true
            }
        }
        Log.d("register1","logowanie0")
        binding.buttonRegisterRegister.setOnClickListener{
                val firstName = binding.edFirstnameRegister.text.toString().trim()
                val lastName = binding.edLastnameRegister.text.toString().trim()
                val email = binding.edEmailRegister.text.toString().trim()
                val password = binding.edPasswordRegister.text.toString().trim()
                val passwordConfirmation = binding.edPasswordConfirmationRegister.text.toString().trim()
                val user = RegisterRequest(firstName, lastName, email, password,passwordConfirmation)
                Log.d("register1","logowanie0")

                val call = RetrofitClient.apiService.registerUser(user)
                Log.d("register1","logowanie")
                call.enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        Log.d("register1","logowanie1")
                        Toast.makeText(applicationContext, "User is registered", Toast.LENGTH_LONG).show()

                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })
            }










    }
}


