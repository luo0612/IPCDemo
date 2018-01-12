package com.luoch.ipcdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        val value: Int

        init {
            val random = Random()
            value = random.nextInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLocalValue.text = value.toString()
        tvLocalValue.setOnClickListener { _ ->
            val intent = Intent()
            intent.action = "com.luoch.ipcdemo.action.REMOTE"
            intent.`package` = packageName
            startActivity(intent)
        }
    }
}
