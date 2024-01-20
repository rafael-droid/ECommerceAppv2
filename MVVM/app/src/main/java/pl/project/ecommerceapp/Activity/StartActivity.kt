package pl.project.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import pl.project.ecommerceapp.Fragment.LoginRegister.LoginActivity
import pl.project.ecommerceapp.Fragment.LoginRegister.RegisterActivity
import pl.project.ecommerceapp.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity(){
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoginAccountOptions.setOnClickListener {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                Log.d("ButtonLogin","kliknięcie")
            }


        binding.buttonRegisterAccountOptions.setOnClickListener{
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
                Log.d("ButtonRegister","kliknięcie")

        }
        binding.tvListProducts.setOnClickListener{
            val intent = Intent(applicationContext, ShoppingActivity::class.java)
            startActivity(intent)
            Log.d("ButtonShopping","kliknięcie")
        }

    }

}




