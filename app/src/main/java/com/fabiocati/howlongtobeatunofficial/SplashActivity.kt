package com.fabiocati.howlongtobeatunofficial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.fabiocati.howlongtobeatunofficial.search.SearchActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlobalScope.launch {
            delay(1000)
            splash_motion_layout.apply {
                transitionToEnd()
            }
            delay(1500)
            val intent = Intent(this@SplashActivity, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}