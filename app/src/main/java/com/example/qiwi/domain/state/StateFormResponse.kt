package com.example.qiwi.domain.state

import com.example.qiwi.domain.dto.ResponseContent

sealed class StateFormResponse {
    data class Result(val result: ResponseContent) : StateFormResponse()
    data class Error(val message: String) : StateFormResponse()
}
