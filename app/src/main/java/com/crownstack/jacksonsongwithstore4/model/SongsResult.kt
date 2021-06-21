package com.crownstack.jacksonsongwithstore4.model

import android.util.Log
import androidx.annotation.NonNull

private const val TAG = "SongsResult"
data class SongsResult<T>(val status: Int, val data: T?) {
    fun isError() = status == STATUS_ERROR
    fun isLoading() = status == STATUS_LOADING
    fun isSuccess() = status == STATUS_SUCCESS

    companion object {
        private const val STATUS_LOADING = 0
        private const val STATUS_SUCCESS = 1
        private const val STATUS_ERROR = -1

        fun <T> success(@NonNull data: T): SongsResult<T> =
            SongsResult(STATUS_SUCCESS, data)

        fun <T> error(item: T? = null): SongsResult<T> {
            Log.d(TAG, "error: ")
            return SongsResult(STATUS_ERROR, item)
        }

        fun <T> loading(data: T? = null): SongsResult<T> =
            SongsResult(STATUS_LOADING, data)

    }
}