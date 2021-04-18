package com.example.qiwi.data.api

import com.example.qiwi.data.model.ResultForm
import retrofit2.http.GET

interface FormApi {
    @GET("form/form.json")
    suspend fun getForm(): ResultForm
}