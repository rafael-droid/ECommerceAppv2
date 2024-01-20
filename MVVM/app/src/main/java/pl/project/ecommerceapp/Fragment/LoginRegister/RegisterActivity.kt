package pl.project.ecommerceapp.Fragment.LoginRegister

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.ActivityLoginBinding
import pl.project.ecommerceapp.databinding.ActivityRegisterBinding

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
            if(binding.edPasswordRegister.length()<8){
                binding.tvErrorPassword.visibility = View.VISIBLE
                check = false
            }else{
                binding.tvErrorPassword.visibility = View.GONE
                check = true
            }
        }
        binding.buttonRegisterRegister.setOnClickListener{
            if(!check){
                binding.tvErrorRegister.visibility = View.VISIBLE
            }

        }


    }
}


