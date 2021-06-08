package com.riis.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel : ViewModel() {
    val gallerItemLiveData: LiveData<List<GalleryItem>> = FlickrFetchr().fetchPhotos()

}