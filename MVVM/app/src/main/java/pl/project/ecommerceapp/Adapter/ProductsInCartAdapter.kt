package pl.project.ecommerceapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import pl.project.ecommerceapp.Api.Constants.BASE_URL
import pl.project.ecommerceapp.Api.RetrofitClient
import pl.project.ecommerceapp.Api.SessionManager
import pl.project.ecommerceapp.Data.CartResponse

import pl.project.ecommerceapp.Data.ItemInCart
import pl.project.ecommerceapp.databinding.CartProductItemBinding



class ProductsInCartAdapter(private val products: List<ItemInCart>): RecyclerView.Adapter<ProductsInCartAdapter.ProductInCartViewHolder>(){

    private lateinit var thisContext: Context
    private var onClickListener: OnClickListener? = null
    private lateinit var sessionManager: SessionManager


    inner class ProductInCartViewHolder(binding: CartProductItemBinding): ViewHolder(binding.root){
        val name = binding.tvProductCartName
        val price = binding.tvProductCartPrice
        val img = binding.imageCartProduct
        val minus = binding.imgMinus
        val plus = binding.imgPlus
        val quantity = binding.tvQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsInCartAdapter.ProductInCartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        thisContext = parent.context
        val binding =  CartProductItemBinding.inflate(inflater, parent,false)
        sessionManager = SessionManager(thisContext)
        return ProductInCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductInCartViewHolder, position: Int) {
        val item = products[position]
        var imageUrl = BASE_URL + products[position].product.thumbnail.url
        Glide.with(thisContext).load(imageUrl).into(holder.img)
        holder.name.text = products[position].product.name
        holder.price.text = products[position].product.price.toString()
        holder.quantity.text = products[position].quantity.toString()
        holder.plus.setOnClickListener{
            products[position].quantity = products[position].quantity.plus(1)
            holder.quantity.text = products[position].quantity.toString()
        }
        holder.minus.setOnClickListener{
            products[position].quantity = products[position].quantity.minus(1)
            holder.quantity.text = products[position].quantity.toString()
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }



}