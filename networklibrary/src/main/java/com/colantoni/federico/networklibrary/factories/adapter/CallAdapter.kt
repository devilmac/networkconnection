package com.colantoni.federico.networklibrary.factories.adapter

import retrofit2.CallAdapter

interface CallAdapter {
    fun getCallAdapter(): CallAdapter.Factory

    interface WithParameter<T> {
        fun getCallAdapter(param: T): CallAdapter.Factory
    }
}
