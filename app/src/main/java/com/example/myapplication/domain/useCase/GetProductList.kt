package com.example.myapplication.domain.useCase

import android.content.Context
import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.repository.Repository
import java.io.File

class GetProductList(private val repository: Repository, private val context: Context) {

    fun get(file: File):MutableList<Product> {
        val data = mutableListOf<Product>()
        file
            .readLines()
            .forEach() {
            if (it.first() != '<') {
            val cargoCode = it.substring(0, it.indexOf(';'))

            var text = it.substring(it.indexOf(';') + 1)
            val shtrichcode = text.substring(0, text.indexOf(';'))

            text = text.substring(text.indexOf(';') + 1)
            val name = text.substring(0, text.indexOf(';'))

            text = text.substring(text.indexOf(';') + 1)
            text = text.substring(text.indexOf(';') + 1)
            val prise = text.substring(0, text.indexOf(';')).toDouble()

                text = text.substring(text.indexOf(';') + 1)
                val remainder = text.substring(0, text.indexOf(';')).toDouble()


            data.add(Product(cargoCode = cargoCode, shtrichcode = shtrichcode, name = name, prise = prise, remainder = remainder))
        } else if (it.first() == '<' && it.contains("attr_id=\"27\"")) {
            val endIndex = it.lastIndexOf('<')
            val startIndex = it.lastIndexOf('>', endIndex - 1) + 1
            val text = it.substring(startIndex, endIndex)
            data.map {hds ->
                if (it.contains("goods_attr id=\"${hds.cargoCode}\"")){
                    hds.alcoholContent = text
                }


            }
        } else if (it.first() == '<' && it.contains("attr_id=\"32\"") && it.contains(">АП<")) {
            data.map {hds ->
                if (it.contains("goods_attr id=\"${hds.cargoCode}\"")){
                    hds.alcoholicBeverages = true
                }
            }
        }
    }
        return data
    }

    suspend fun saveToFile(text:String): File {
        val file = context.filesDir.resolve(text)
        file.appendText(repository.getProduct().string())
        return file
    }
}