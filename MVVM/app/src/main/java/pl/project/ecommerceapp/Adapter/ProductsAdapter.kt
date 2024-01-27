package pl.project.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import pl.project.ecommerceapp.Api.Constants.BASE_URL
import pl.project.ecommerceapp.Data.Item
import pl.project.ecommerceapp.databinding.ProductRvItemBinding


class ProductsAdapter(private val products: List<Item>): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    private lateinit var thisContext: Context
    private var onClickListener: OnClickListener? = null


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
        val item = products[position]
        var imageUrl = BASE_URL + products[position].thumbnail.url
        Glide.with(thisContext).load(imageUrl).into(holder.img)
        holder.name.text = products[position].name
        holder.price.text = products[position].price.toString()

        holder.itemView.setOnClickListener{
            if(onClickListener != null){
                onClickListener!!.onClick(position,item)
            }
        }

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }


    fun interface OnClickListener {
        fun onClick(position: Int, model: Item)
    }

}