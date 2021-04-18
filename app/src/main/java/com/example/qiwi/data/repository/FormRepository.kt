package com.example.qiwi.data.repository

import com.example.qiwi.data.api.FormApi
import com.example.qiwi.data.mapper.Mapper
import com.example.qiwi.domain.dto.ResponseContent
import com.example.qiwi.domain.state.StateFormResponse
import java.lang.Exception
import javax.inject.Inject

class FormRepository @Inject constructor(private val formApi: FormApi) {
    suspend fun getFormServer(): StateFormResponse {
        return try {
            val result = formApi.getForm()
            StateFormResponse.Result(Mapper.mapperFormToDto(result.content))
        } catch (e: Exception) {
            StateFormResponse.Error(e.message.toString())
        }
    }
}