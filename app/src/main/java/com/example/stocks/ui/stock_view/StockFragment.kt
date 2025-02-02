package com.example.stocks.ui.stock_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stocks.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel : StockViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val stockAdapter = StockListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchStock()
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                binding.progressView.isVisible = it.showLoader
                if(it.showError){
                    it.errorText?.let { error->
                        showSnackBar(error)
                    }
                }
                if(it.stocks.isNotEmpty()){
                    binding.stockRecyclerView.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                        adapter = stockAdapter
                    }
                    stockAdapter.data = it.stocks
                }
            }
        }

        binding.button.setOnClickListener {
            if(binding.etStock.text.toString().isEmpty()){
                binding.textInputLayout.error = "Enter valid input"
                return@setOnClickListener
            }
            if(binding.etStock.text.toString().equals(viewModel.lastSearch, true)){
                showSnackBar("You can refresh the data by pulling down the list")
                return@setOnClickListener
            }
            binding.textInputLayout.error = null
            if(binding.etStock.text.toString().isNotEmpty()){
                viewModel.fetchStock(binding.etStock.text.toString())
            }
        }

        binding.refresh.setOnRefreshListener {
            viewModel.fetchStock()
            binding.refresh.isRefreshing = false
        }
    }


    private fun showSnackBar(error: String = "Something went wrong"){
        Snackbar.make(binding.root, error , Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}