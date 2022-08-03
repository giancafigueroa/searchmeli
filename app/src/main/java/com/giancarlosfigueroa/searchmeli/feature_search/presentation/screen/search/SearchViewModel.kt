package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.search


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giancarlosfigueroa.searchmeli.feature_search.data.remote.SearchService
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchViewModel() : ViewModel() {



    val REQUIRED_ERROR = "No puede ser vacio"

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state



    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.EnteredSearch -> {
                val error = if (event.value.isBlank()) REQUIRED_ERROR else ""
                _state.value = state.value.copy(
                    searchValue = event.value,
                    error = error
                )
            }
            is SearchEvent.PutError -> {
                _state.value = state.value.copy(
                    error = event.value
                )
            }
        }
    }


}

