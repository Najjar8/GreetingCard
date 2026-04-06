package com.example.greetingcard.designsystem

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.data.AppDatabase
import com.example.greetingcard.data.ImageEntity
import com.example.greetingcard.data.ImageRepository
import com.example.greetingcard.network.GalleryPhoto
import com.example.greetingcard.network.ImgApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException




//-----------------------Gallery view model code-------------------------

sealed interface ImgUiState {
    data class Success(val photos: List<GalleryPhoto>) : ImgUiState
    object Error : ImgUiState
    object Loading : ImgUiState
}


class ImageViewModel(application: Application) : AndroidViewModel(application){

    var imgUiState: ImgUiState by mutableStateOf(ImgUiState.Loading)
        private set

    private val repository = ImageRepository(
        AppDatabase.getInstance(application).imageDao()
    )

    init {
        getGalleryPhotos()
    }


    fun getGalleryPhotos() {
        viewModelScope.launch {
            imgUiState = ImgUiState.Loading
            val photos = repository.getPhotos()
            imgUiState = if (photos != null) {
                ImgUiState.Success(photos)
            } else {
                ImgUiState.Error
            }
        }
    }



}