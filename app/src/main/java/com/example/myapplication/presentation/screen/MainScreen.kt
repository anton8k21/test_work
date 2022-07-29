package com.example.myapplication.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Product
import com.example.myapplication.databinding.FragmentMainScreenBinding
import com.example.myapplication.presentation.Adapter
import com.example.myapplication.presentation.OnInteractionListener
import com.example.myapplication.presentation.viewModel.ViewModel

class MainScreen : Fragment() {

    val viewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainScreenBinding.inflate(layoutInflater)

        val recyclerView =
            binding.root.findViewById(R.id.recycle_view) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 1)

        viewModel.data.observe(viewLifecycleOwner) {
            val adapter = Adapter(it, object : OnInteractionListener {
                override fun openInfo(product: Product) {
                    findNavController().navigate(R.id.infoScreen)
                    viewModel.setProduct(product)
                }
            })
            recyclerView.adapter = adapter
        }
        return binding.root
    }
}