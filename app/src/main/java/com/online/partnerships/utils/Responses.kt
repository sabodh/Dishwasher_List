package com.online.partnerships.utils

/**
 * Generic Response handler created to handle the api responses
 * inside the repositories.
 */
data class Responses<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Responses<T> {
            return Responses(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Responses<T> {
            return Responses(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Responses<T> {
            return Responses(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

