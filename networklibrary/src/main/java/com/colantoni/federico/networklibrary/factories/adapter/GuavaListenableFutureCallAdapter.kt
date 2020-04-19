package com.colantoni.federico.networklibrary.factories.adapter

import retrofit2.adapter.guava.GuavaCallAdapterFactory

object GuavaListenableFutureCallAdapter : CallAdapter {
    override fun getCallAdapter(): retrofit2.CallAdapter.Factory = GuavaCallAdapterFactory.create()
}
