package com.example.qiwi.presentation.activity

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.mvvm.ModelFactory
import com.example.qiwi.R
import com.example.qiwi.di.ActivityComponent
import com.example.qiwi.presentation.adapter.FormAdapter
import com.example.qiwi.presentation.viewModel.QiwiViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var qiwiViewModel: QiwiViewModel

    @Inject
    lateinit var adapterForm: FormAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityComponent.create(this).inject(this)
        qiwiViewModel =
            ViewModelProvider(this, ModelFactory(qiwiViewModel)).get(qiwiViewModel::class.java)
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterForm
        }
        qiwiViewModel.getVisibleView().observe(this, {
            adapterForm.submitList(it)
        })
        qiwiViewModel.getErrorMessages().observe(this, {
            toast(it)
        })
        qiwiViewModel.stateKeyBoard().observe(this, {
            hideBroad()
        })

        findViewById<Button>(R.id.validationBt).setOnClickListener {
            qiwiViewModel.validation()
        }
        qiwiViewModel.init()
    }

    private fun hideBroad() {
        val inputManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            this.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}