package com.luoch.ipcdemo

import android.content.Context
import android.net.Uri
import android.util.Log

/**
 * Created by luoch on 2018/1/12.
 */
class IPCWrapper {
    companion object {
        val TAG: String = "IPCWrapper"
        val VALUE = "value"

        fun getLocalValue(context: Context, field: String?): String? {
            logi("getLocalValue : " + field)
            val uri = getIPCUriForField(field)
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            var value: String? = null
            if (cursor != null) {
                cursor.moveToFirst()
                value = cursor.getString(1)
                cursor.close()
            }
            return value
        }

        private fun getIPCUriForField(field: String?): Uri {
            return Uri.withAppendedPath(IPCProvider.ipcUriBase, field ?: "")
        }

        fun logi(log: Any?) {
            Log.i(TAG, log?.toString() ?: "null")
        }
    }
}