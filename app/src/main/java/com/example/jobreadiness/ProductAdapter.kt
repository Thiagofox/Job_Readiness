package com.example.jobreadiness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobreadiness.product.Products
import com.example.jobreadiness.ui.ItemModel

class ProductAdapter(private val itemList: List<Products>, val itemSelected:(Products) -> Unit) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.product_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.productName.text = item.body.title
        holder.price.text = item.body.price

        holder.itemView.setOnClickListener{itemSelected(item)}
    }

    override fun getItemCount() = itemList.size


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productName: TextView = itemView.findViewById(R.id.text_product_name)
        val price: TextView = itemView.findViewById(R.id.text_price)
        val image: ImageView = itemView.findViewById(R.id.image_product_img)
    }
}
