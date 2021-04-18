package com.example.qiwi.data.mapper

import com.example.qiwi.data.model.Choices
import com.example.qiwi.data.model.ResultForm
import com.example.qiwi.data.model.Validator
import com.example.qiwi.data.model.View
import com.example.qiwi.domain.dto.*

object Mapper {
    fun mapperFormToDto(content: ResultForm.Form?): ResponseContent {
        val visibleView = ArrayList<ViewDto>()
        val hideView = hashMapOf<String, ArrayList<ViewDto>>()

        content?.elements?.forEach { itemContent ->
            if (itemContent.type == "field") {
                mapperVisibleView(visibleView, itemContent)
            }
            if (itemContent.type == "dependency") {
                mapperDependencyView(hideView, itemContent)
            }
        }
        return ResponseContent(visibleView, hideView)
    }

    private fun mapperDependencyView(
        hasMapView: HashMap<String, ArrayList<ViewDto>>,
        itemContent: ResultForm.Form.ItemForm
    ) {
        val hideView = ArrayList<ViewDto>()
        itemContent.content?.elements?.forEach { dependencyContent ->
            if (dependencyContent.type == "field") {
                dependencyContent.view?.let {
                    hideView.add(
                        mapperToViewDto(it, dependencyContent.validator)
                    )
                }
            }
        }
        val key = itemContent.condition?.predicate?.pattern ?: ""
        if (hasMapView.containsKey(key)) {
            val list = hasMapView[key]
            list?.addAll(hideView)
        } else {
            hasMapView[key] = hideView
        }
    }

    private fun mapperVisibleView(
        visibleView: MutableList<ViewDto>,
        itemContent: ResultForm.Form.ItemForm
    ) {
        itemContent.view?.let {
            visibleView.add(
                mapperToViewDto(it, itemContent.validator)
            )
        }
    }

    private fun mapperToViewDto(view: View, validator: Validator?): ViewDto {
        return ViewDto(
            title = view.title ?: "",
            widget = WidgetDto(
                type = view.widget?.type ?: "",
                mapperChoicesDto(view.widget?.choices)
            ),
            ValidatorDto(
                pattern = validator?.predicate?.pattern ?: "",
                message = validator?.message ?: ""
            ), if (view.widget?.type == "radio") 0 else 1
        )
    }

    private fun mapperChoicesDto(choices: List<Choices>?): List<ChoicesDto> {
        val choicesList = mutableListOf<ChoicesDto>()
        choices?.forEach {
            choicesList.add(ChoicesDto(it.value ?: "", it.title ?: ""))
        }
        return choicesList
    }
}