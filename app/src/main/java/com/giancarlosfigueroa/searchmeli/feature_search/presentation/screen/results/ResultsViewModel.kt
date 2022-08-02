package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.results


import androidx.lifecycle.ViewModel
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val productUseCase:ProductUseCases
): ViewModel() {
}