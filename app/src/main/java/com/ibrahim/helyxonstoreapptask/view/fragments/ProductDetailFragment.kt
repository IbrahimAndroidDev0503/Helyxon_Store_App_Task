package com.ibrahim.helyxonstoreapptask.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.ibrahim.helyxonstoreapptask.R
import com.ibrahim.helyxonstoreapptask.databinding.FragmentProductDetailBinding
import com.ibrahim.helyxonstoreapptask.model.ProductData
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)

        setData()
        setListeners()

        return binding.root
    }

    //action or click listeners
    private fun setListeners() {
        binding.orderBTN.setOnClickListener {
            Toast.makeText(requireContext(), "Ordered Successful", Toast.LENGTH_SHORT).show()
        }

        binding.backCV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    //To bind data with ui
    private fun setData() {
        val productData = arguments?.getSerializable("PRODUCT_DATA") as? ProductData

        if (productData != null) {
            binding.viewProductNameTV.text = productData.title
            binding.viewProductPriceTV.text = "₹${productData.price.toString()}"
            binding.viewProductCategoryTV.text = productData.category
            binding.viewProductDescriptionTV.text = productData.description
            binding.viewProductRatingTV.text = productData.rating?.rate.toString()
            binding.viewProductRatingCountTV.text = "(${productData.rating?.count.toString()})"
            binding.ratingBar.rating = productData.rating?.rate?.toFloat() ?: 0.0F
            Picasso.get().load(productData.image).placeholder(R.drawable.placeholder).into(binding.viewProductIV)
        }
    }
}