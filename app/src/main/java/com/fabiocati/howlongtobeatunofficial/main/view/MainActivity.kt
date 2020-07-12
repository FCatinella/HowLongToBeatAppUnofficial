package com.fabiocati.howlongtobeatunofficial.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.main.viewModel.MainActivityVM
import com.fabiocati.howlongtobeatunofficial.startSearchActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {
    lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainActivityVM()
        setUI()
    }

    private fun setUI() {
        search_button.setOnClickListener {
            startSearchActivity(this, editText.text.toString())
        }
    }

}