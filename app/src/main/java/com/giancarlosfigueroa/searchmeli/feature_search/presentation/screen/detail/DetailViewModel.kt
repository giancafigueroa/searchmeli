package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.detail


import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productUseCase:ProductUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: MutableState<Product?> = _product
    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _product.value=productUseCase.getProductById(id)
            }
        }
    }
}