package com.rasenyer.showsapp.repo

import com.rasenyer.showsapp.data.remote.ShowsApi
import javax.inject.Inject

class ShowsRepository @Inject constructor(private val showsApi: ShowsApi) {

    suspend fun getShows() = showsApi.getShows()

}