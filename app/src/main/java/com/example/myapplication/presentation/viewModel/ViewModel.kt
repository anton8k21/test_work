package com.example.myapplication.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.ApiService
import com.example.myapplication.domain.model.Product
import com.example.myapplication.data.repositoryImpl.RepositoryImpl
import com.example.myapplication.domain.model.State
import com.example.myapplication.domain.useCase.GetProductList
import kotlinx.coroutines.launch
import java.lang.Exception

open class ViewModel(application: Application) : AndroidViewModel(application) {

    private var _data: MutableLiveData<List<Product>> = MutableLiveData()
    val data: MutableLiveData<List<Product>> = _data

    private var _dataItem: MutableLiveData<Product> = MutableLiveData()
    val dataItem: MutableLiveData<Product> = _dataItem

    private var _dataState: MutableLiveData<State> = MutableLiveData()
    val dataState: MutableLiveData<State> = _dataState

    private val repository by lazy {
        RepositoryImpl(apiService = ApiService)
    }
    private val getUseCase by lazy {
        GetProductList(repository, application)
    }
    fun setProduct(product: Product){
        dataItem.value = product
    }

    init {
        load()
    }

    fun load() = viewModelScope.launch {
        try {
            _dataState.postValue(State(loading = true, error = false))
            val file = getUseCase.saveToFile("catalog.spr")
            data.postValue(getUseCase.get(file))
            _dataState.postValue(State(loading = false, error = false))
        }catch (e:Exception){
            _dataState.postValue(State(loading = false, error = true))
        }

    }


}
