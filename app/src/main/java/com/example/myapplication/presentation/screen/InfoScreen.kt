package com.example.myapplication.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentInfoBinding
import com.example.myapplication.presentation.viewModel.ViewModel

class InfoScreen: Fragment() {
    val viewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInfoBinding.inflate(layoutInflater)
        viewModel.dataItem.observe(viewLifecycleOwner){
            binding.apply {
                cargoCode.text = it.cargoCode
                remainder.text = it.remainder.toString()
                alcoholContent.text = it.alcoholContent
                name.text = it.name
                price.text = it.prise.toString()
                retailPrice.text = String.format("%.2f", it.prise * it.remainder)
                AlcoholicBeverages.text = if (it.alcoholicBeverages) "ДА" else "НЕТ"
            }
        }
        return binding.root
    }
}