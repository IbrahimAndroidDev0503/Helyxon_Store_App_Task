package com.ibrahim.helyxonstoreapptask.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.ibrahim.helyxonstoreapptask.R
import com.ibrahim.helyxonstoreapptask.model.ProductData
import com.squareup.picasso.Picasso

class ProductListAdapter(private val products: ArrayList<ProductData>, val context: Context) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onProductClick(products: ProductData, position: Int)
    }

    fun setOnClickListeners(onItemClick:OnItemClickListener) {
        mOnItemClickListener = onItemClick
    }

    inner class ProductListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ShapeableImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productCL: ConstraintLayout = view.findViewById(R.id.productCL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_adapter_layout, parent, false)
        return ProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.image).placeholder(R.drawable.placeholder).into(holder.productImage)
        holder.productName.text = product.title
        holder.productPrice.text = "₹${product.price.toString()}"
        holder.productDescription.text = product.description
        holder.productCL.setOnClickListener {
            mOnItemClickListener?.onProductClick(product,position)
        }
    }

    override fun getItemCount() = products.size

    // Update the data list
    fun updateData(newList: ArrayList<ProductData>) {
        this.products.clear()
        this.products.addAll(newList)
        notifyDataSetChanged()
    }
}