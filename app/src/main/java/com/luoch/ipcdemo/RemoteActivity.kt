package com.luoch.ipcdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_remote.*

/**
 * Created by luoch on 2018/1/12.
 */
class RemoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)
        tvRemoteValue.text = IPCWrapper.getLocalValue(applicationContext, IPCWrapper.VALUE)
    }

}