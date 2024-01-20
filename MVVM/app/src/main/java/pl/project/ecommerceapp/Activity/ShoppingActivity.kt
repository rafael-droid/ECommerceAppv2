package pl.project.ecommerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import pl.project.ecommerceapp.Fragment.Shopping.CartFragment
import pl.project.ecommerceapp.Fragment.Shopping.HomeFragment
import pl.project.ecommerceapp.Fragment.Shopping.ProfileFragment
import pl.project.ecommerceapp.Fragment.Shopping.ScanFragment

import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.ActivityShoppingBinding


class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {


                when (it.itemId) {
                    R.id.homeIcon -> {
                        loadFragment(HomeFragment())
                        true
                    }

                    R.id.cartIcon -> {
                        loadFragment(CartFragment())
                        true
                    }

                    R.id.profileIcon -> {
                        loadFragment(ProfileFragment())
                        true
                    }
                    R.id.scanIcon -> {
                        loadFragment(ScanFragment())
                        true
                    }

                    else -> {}
                }
            true
        }


    }
    private fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}