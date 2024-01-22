package pl.project.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import pl.project.ecommerceapp.Api.Constants.BASE_URL
import pl.project.ecommerceapp.Data.Item
import pl.project.ecommerceapp.Fragment.Shopping.ProductDetailFragment
import pl.project.ecommerceapp.R
import pl.project.ecommerceapp.databinding.ProductRvItemBinding


class ProductsAdapter(private val products: List<Item>): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    private lateinit var thisContext: Context
    inner class ProductViewHolder(binding: ProductRvItemBinding): ViewHolder(binding.root){
        val img = binding.imgProduct
        val name = binding.tvName
        val price = binding.tvPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        thisContext = parent.context
        val binding =  ProductRvItemBinding.inflate(inflater, parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
            return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var imageUrl = BASE_URL + products[position].thumbnail.url
        Glide.with(thisContext).load(imageUrl).into(holder.img)
        holder.name.text = products[position].name
        holder.price.text = products[position].price.toString()

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val productDetailFragment= ProductDetailFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.homeFragment,productDetailFragment).commit()
            }

        })

    }
}