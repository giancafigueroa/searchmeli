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
import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidId
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.results.ResultsViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productUseCase:ProductUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: MutableState<Product?> = _product


    private val _eventFlow = MutableSharedFlow<DetailViewModel.UiEventDetail>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                try {
                    _product.value=productUseCase.getProductById(id)

                }catch (e:InvalidId){
                    _eventFlow.emit(
                        UiEventDetail.ShowToast(
                            message = "El valor no puede ser vacio"
                        )
                    )
                }
            }
        }
    }
    sealed class UiEventDetail {
        data class ShowToast(val message: String) : DetailViewModel.UiEventDetail()
    }
}