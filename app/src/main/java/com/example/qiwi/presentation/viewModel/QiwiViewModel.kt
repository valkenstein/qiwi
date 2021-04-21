package com.example.qiwi.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.domain.state.StateFormResponse
import com.example.qiwi.domain.useCase.QiwiUseCase
import kotlinx.coroutines.*

class QiwiViewModel constructor(private val qiwiUseCase: QiwiUseCase) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val errorValidation = HashMap<ViewDto, String>()
    private val showErrorMessages = MutableLiveData<String>()
    private val showView = MutableLiveData<MutableList<ViewDto>>()
    private val visibleView = MutableLiveData<MutableList<ViewDto>>()
    private val hiddenView = MutableLiveData<HashMap<String, ArrayList<ViewDto>>>()
    private val hideKeyBoard = MutableLiveData<Boolean>()

    fun stateKeyBoard(): LiveData<Boolean> = hideKeyBoard
    fun getVisibleView(): LiveData<MutableList<ViewDto>> = showView
    fun getErrorMessages(): LiveData<String> = showErrorMessages

    fun init() {
        if (showView.value.isNullOrEmpty())
            getForm()
    }

    private fun getForm() {
        scope.launch {
            when (val response = getFormAsync()) {
                is StateFormResponse.Result -> {
                    showView.postValue(response.result.visibleView)
                    visibleView.postValue(response.result.visibleView)
                    hiddenView.postValue(response.result.hideView)
                }
                is StateFormResponse.Error -> {
                    showErrorMessages.postValue(response.message)
                }
            }
        }
    }

    private suspend fun getFormAsync() = withContext(Dispatchers.IO) {
        qiwiUseCase.getForm()
    }

    fun selectItem(value: String, viewDto: ViewDto) {
        val regex = viewDto.validator.pattern.toRegex()
        when {
            !regex.matches(value) -> {
                if (value.isEmpty())
                    errorValidation.remove(viewDto)
                else
                    errorValidation[viewDto] = viewDto.validator.message
            }
            else -> {
                addDependencyView(value, viewDto)
            }
        }
    }

    private fun addDependencyView(value: String, viewDto: ViewDto) = scope.launch {
        hideKeyBoard.postValue(true)
        errorValidation.remove(viewDto)
        hiddenView.value?.forEach {
            if (it.key.toRegex().matches(value)) {
                val viewList = ArrayList<ViewDto>()
                viewList.addAll(visibleView.value!!)
                viewList.addAll(it.value)
                showView.postValue(viewList)
            }
        }
    }

    fun validation() {
        errorValidation.let {
            if (it.isNotEmpty())
                showErrorMessages.postValue(it.values.toString())
        }
    }
}