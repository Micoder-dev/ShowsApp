package com.rasenyer.showsapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasenyer.showsapp.data.models.Show
import com.rasenyer.showsapp.repo.ShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val showsRepository: ShowsRepository): ViewModel() {

    private val show = MutableLiveData<Show>()

    fun getShowById(id: Int) = viewModelScope.launch {

        showsRepository.getShows().let {

            if (it.isSuccessful) {

                for (showResponse in it.body()!!) {

                    if (showResponse.id == id) {

                        show.value = showResponse

                    }

                }

            } else {

                Log.e("Error: ", it.code().toString())

            }

        }

    }

    fun observeShow(): LiveData<Show> {
        return show
    }

}