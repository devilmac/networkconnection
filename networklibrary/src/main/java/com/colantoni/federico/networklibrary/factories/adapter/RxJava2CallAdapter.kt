package com.colantoni.federico.networklibrary.factories.adapter

import io.reactivex.Scheduler
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RxJava2CallAdapter : CallAdapter, CallAdapter.WithParameter<Scheduler> {
    override fun getCallAdapter(): retrofit2.CallAdapter.Factory =
            RxJava2CallAdapterFactory.create()

    override fun getCallAdapter(param: Scheduler): retrofit2.CallAdapter.Factory =
            RxJava2CallAdapterFactory.createWithScheduler(param)
}
