package pl.project.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import pl.project.ecommerceapp.databinding.ActivityIntroductionBinding



class IntroductionAcitivity: AppCompatActivity() {
    private lateinit var binding: ActivityIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIntro.setOnClickListener {
            val intent = Intent(applicationContext, StartActivity::class.java)
            startActivity(intent)
            Log.d("ButtonIntro","kliknięcie")
        }




    }
}