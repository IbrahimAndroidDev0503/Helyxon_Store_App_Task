package com.ibrahim.helyxonstoreapptask.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.helyxonstoreapptask.R
import com.ibrahim.helyxonstoreapptask.databinding.FragmentProductListBinding
import com.ibrahim.helyxonstoreapptask.model.ProductData
import com.ibrahim.helyxonstoreapptask.view.adapters.ProductListAdapter
import com.ibrahim.helyxonstoreapptask.viewmodel.ProductViewModel
import com.ibrahim.helyxonstoreapptask.viewmodel.ProductViewModelFactory

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(layoutInflater)

        val factory = ProductViewModelFactory(requireContext())
        productViewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        setAdapter()
        observeViewModel()
        setListeners()
        productViewModel.fetchProductList()

        productViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        return binding.root
    }

    //action or click listeners
    private fun setListeners() {
        binding.sortCL.setOnClickListener {
            SortBottomSheetFragment.show(childFragmentManager) { sortOrder ->
                sortProducts(sortOrder)
            }
        }

        binding.retryBTN.setOnClickListener {
            binding.noNetworkIV.visibility = View.GONE
            binding.retryBTN.visibility = View.GONE
            binding.productRV.visibility = View.VISIBLE
            productViewModel.fetchProductList()
        }

        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                filterProducts(query)
            }

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
        binding.searchET.clearFocus()
        binding.searchET.setText("")
    }

    //setting the adapter into Recyclerview
    private fun setAdapter() {
        binding.productRV.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductListAdapter(ArrayList(), requireContext())
        binding.productRV.adapter = adapter
        adapter.setOnClickListeners(object : ProductListAdapter.OnItemClickListener {
            override fun onProductClick(products: ProductData, position: Int) {
                navigateToProductDetail(products)
            }
        })
    }

    //to filter the data while searching
    private fun filterProducts(query: String) {
        val filteredList = productViewModel.productList.value?.filter { product ->
            product.title?.contains(query, ignoreCase = true) == true
        }

        if (filteredList != null) {
            adapter.updateData(ArrayList(filteredList))
        }
    }

    //to save sort order and update the data order
    private fun sortProducts(sortOrder: String) {
        val sharedPreferences = requireContext().getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().putString("SORT_ORDER", sortOrder).apply()

        val sortedList = when (sortOrder) {
            "Low to High" -> productViewModel.productList.value?.sortedBy { it.price }
            "High to Low" -> productViewModel.productList.value?.sortedByDescending { it.price }
            else -> productViewModel.productList.value
        }
        if (sortedList != null) {
            adapter.updateData(ArrayList(sortedList))
        }
    }

    //to get saved sort order
    private fun getSavedSortOrder(): String {
        val sharedPreferences = requireContext().getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getString("SORT_ORDER", "Low to High") ?: "Low to High"
    }

    //passing data and moving to product detail fragment
    private fun navigateToProductDetail(products: ProductData) {
        val productDetailFragment = ProductDetailFragment()

        val bundle = Bundle()
        bundle.putSerializable("PRODUCT_DATA", products)
        productDetailFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productDetailFragment)
            .addToBackStack(null)
            .commit()
    }

    //to observe data from viewModel
    private fun observeViewModel() {
        productViewModel.productList.observe(viewLifecycleOwner, { products ->
            val sort = getSavedSortOrder()
            sortProducts(sort)
        })

        productViewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            if (error.contains("No internet connection")) {
                binding.noNetworkIV.visibility = View.VISIBLE
                binding.retryBTN.visibility = View.VISIBLE
                binding.productRV.visibility = View.GONE
            } else {
                binding.noNetworkIV.visibility = View.GONE
                binding.retryBTN.visibility = View.GONE
                binding.productRV.visibility = View.VISIBLE
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
