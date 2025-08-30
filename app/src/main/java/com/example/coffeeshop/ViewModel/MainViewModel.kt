//package com.example.coffeeshop.ViewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.example.coffeeshop.Domain.BannerModel
//import com.example.coffeeshop.Repositry.MainRepository
//
//class MainViewModel : ViewModel() {
//    private val repository = MainRepository()
//
//    fun loadBanner(): LiveData<MutableList<BannerModel>> {
//
//        return repository.loadBanner()
//    }
//}
//

package com.example.coffeeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.Domain.BannerModel
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.Domain.itemsModel
import com.example.coffeeshop.Repositry.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository = MainRepository()) : ViewModel() {
    private val _banners = MutableLiveData<List<BannerModel>>()
    val banners: LiveData<List<BannerModel>> = _banners

    fun loadBanner() {
        viewModelScope.launch {
            try {
                repository.loadBanner().observeForever { bannerList ->
                    _banners.postValue(bannerList.toList()) // Convert to immutable list
                }
            } catch (e: Exception) {
                _banners.postValue(emptyList())
            }
        }
    }

    override fun onCleared() {
        repository.loadBanner().removeObserver { } // Clean up observers
        super.onCleared()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
         return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<itemsModel>> {
        return repository.loadPopular()
    }

    fun loadItem(categoryID: String): LiveData<MutableList<itemsModel>> {
        return repository.loadItemCategory(categoryID)

    }
}

