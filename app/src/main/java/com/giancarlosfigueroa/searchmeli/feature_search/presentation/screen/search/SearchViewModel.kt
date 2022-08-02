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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productUseCase:ProductUseCases
): ViewModel() {
    private  val service=SearchService.create()

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state



    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    searchValue = event.value
                )
            }
            is SearchEvent.Search->{
                GlobalScope.launch {
                    val results=service.search(state.value.searchValue)
                }


            }


            else -> {}
        }
    }
}