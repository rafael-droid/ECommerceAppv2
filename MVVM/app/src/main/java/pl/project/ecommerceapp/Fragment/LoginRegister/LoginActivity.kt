package pl.project.ecommerceapp.Fragment.LoginRegister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import okhttp3.ResponseBody
import pl.project.ecommerceapp.Activity.ShoppingActivity
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager
import pl.project.ecommerceapp.Data.LoginRequest
import pl.project.ecommerceapp.Data.LoginResponse
import pl.project.ecommerceapp.Data.User
import pl.project.ecommerceapp.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity: AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var check: Boolean = false

        binding.buttonLoginLogin.setOnClickListener{
            if(binding.edEmailLogin.length()<5 || !binding.edEmailLogin.text.toString().contains("@") || binding.edPasswordLogin.length()<8){
                binding.tvErrorLogin.visibility = View.VISIBLE
                check = false
            }else{
                check = true
            }
        }


        binding.tvDontHaveAccount.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }


        sessionManager = SessionManager(this)
        binding.buttonLoginLogin.setOnClickListener {
            val email = binding.edEmailLogin.text.toString().trim()
            val password = binding.edPasswordLogin.text.toString().trim()
            Log.d("email", email)
            Log.d("password", password)
            val user = LoginRequest(email, password)


                val call = RetrofitClient.apiService.loginUser(user)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null) {
                                Log.d("Login", loginResponse.token)

                                sessionManager.saveAuthToken(loginResponse.token)

                                Toast.makeText(applicationContext, "Login", Toast.LENGTH_LONG)
                                    .show()

                                val intent =
                                    Intent(applicationContext, ShoppingActivity::class.java)
                                startActivity(intent)
                            }

                        }


                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })

        }
}
}


