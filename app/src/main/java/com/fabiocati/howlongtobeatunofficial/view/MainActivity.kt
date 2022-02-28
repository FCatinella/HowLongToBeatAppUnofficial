package com.fabiocati.howlongtobeatunofficial.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.startSearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
    }

    private fun setUI() {
        search_button.setOnClickListener {
            startSearchActivity(this, editText.text.toString())
        }
    }
}