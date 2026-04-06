package com.example.greetingcard.data

import com.example.greetingcard.network.GalleryPhoto
import com.example.greetingcard.network.ImgApi
import kotlinx.coroutines.flow.first
import java.io.IOException

class ImageRepository(private val imageDao: ImageDao) {


    suspend fun getPhotos(): List<GalleryPhoto>? {
        return try {
            getNetworkPhotos()
        } catch (e: IOException) {
            getCachedPhotos()
        }
    }

    suspend fun getNetworkPhotos(): List<GalleryPhoto> {
            val photos = ImgApi.retrofitService.getPhotos()

            if (!imageDao.hasImages().first()) {
                photos.takeLast(5).forEach { photo ->
                    imageDao.insertImages(ImageEntity(link = photo.imgSrc))
                }
            }

            return photos
    }

    suspend fun getCachedPhotos(): List<GalleryPhoto>? {
        val cached = imageDao.getAllImages()

        return if (cached.isNotEmpty()) {
            cached.map { GalleryPhoto(imgSrc = it.link) }
        } else {
            null // nothing from network or cache
        }
    }


}