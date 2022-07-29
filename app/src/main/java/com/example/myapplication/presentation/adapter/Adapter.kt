package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.model.Product
import com.example.myapplication.databinding.FragmentItemListBinding


interface OnInteractionListener {
    fun openInfo(product: Product){}
}
class Adapter(
    private val listProduct: List<Product>,
    private val onInteractionListener: OnInteractionListener
): RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            FragmentItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = listProduct[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = listProduct.size
}


class Holder(
    private val binding: FragmentItemListBinding,
    private val onInteractionListener: OnInteractionListener
): RecyclerView.ViewHolder(binding.root){
    fun bind(product: Product){
        binding.apply {
            name.text = product.name
            remainder.text = product.remainder.toString()
            prise.text = product.prise.toString()
        cargoCode.text = product.cargoCode
            binding.root.setOnClickListener{
                onInteractionListener.openInfo(product)
            }
        }

    }

}