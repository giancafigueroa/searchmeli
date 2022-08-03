package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.results


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val productUseCase:ProductUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _resultsItems = mutableStateListOf<Product>()
    val resultsItems: MutableList<Product> = _resultsItems

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("q")?.let { q ->
            if(q != "") {
                viewModelScope.launch {
                    val items=productUseCase.searchProducts(q.trim())
                    if(items.isNotEmpty()){
                        _resultsItems.addAll(items)
                    }else{

                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = "No encontro ningun resultado"
                            )
                        )
                    }

                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }
}
