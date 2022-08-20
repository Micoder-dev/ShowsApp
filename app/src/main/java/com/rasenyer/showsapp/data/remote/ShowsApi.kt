package com.rasenyer.showsapp.data.remote

import com.rasenyer.showsapp.data.models.Shows
import com.rasenyer.showsapp.util.Constants.Companion.SHOWS
import retrofit2.Response
import retrofit2.http.GET

interface ShowsApi {

    @GET(SHOWS)
    suspend fun getShows(): Response<Shows>

}