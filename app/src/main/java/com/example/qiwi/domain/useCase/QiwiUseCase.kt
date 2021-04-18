package com.example.qiwi.domain.useCase

import com.example.qiwi.data.repository.FormRepository
import javax.inject.Inject

class QiwiUseCase @Inject constructor(private val formRepository: FormRepository) {
    suspend fun getForm() = formRepository.getFormServer()
}