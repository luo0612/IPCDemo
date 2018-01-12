package com.luoch.ipcdemo

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import java.util.regex.Matcher

/**
 * Created by luoch on 2018/1/12.
 */
class IPCProvider : ContentProvider() {

    companion object {
        val tag: String = "IPCProvider"
        val ipc: Int = 1
        val ipcType: String = "ipc_type"
        private val authority = "com.luoch.ipcdemo"
        private val ipcTableName: String = "IPC"
        val fieldName = "name"
        val fieldValue = "value"

        val ipcUriBase = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority + "/" + ipcTableName + "/")!!

        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(authority, ipcTableName, ipc)
            uriMatcher.addURI(authority, ipcTableName + "/*", ipc)
        }
    }


    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        return null
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val mc = MatrixCursor(arrayOf(fieldName, fieldValue))
        if (uriMatcher.match(uri) == ipc) {
            val field = uri?.lastPathSegment
            var value: String?
            value = when (field) {
                IPCWrapper.VALUE -> MainActivity.value.toString()
                else -> "-1"
            }
            mc.addRow(arrayOf(field, value))
        }
        return mc
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri?): String {
        when (uriMatcher.match(uri)) {
            ipc -> return ipcType
            else -> throw IllegalArgumentException("Unknown URI " + uri)
        }
    }

}