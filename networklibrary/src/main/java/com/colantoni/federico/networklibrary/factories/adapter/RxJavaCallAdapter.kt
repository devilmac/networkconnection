package com.colantoni.federico.networklibrary.factories.adapter

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Scheduler

object RxJavaCallAdapter : CallAdapter, CallAdapter.WithParameter<Scheduler> {
    override fun getCallAdapter(): retrofit2.CallAdapter.Factory = RxJavaCallAdapterFactory.create()

    override fun getCallAdapter(param: Scheduler): retrofit2.CallAdapter.Factory =
            RxJavaCallAdapterFactory.createWithScheduler(param)
}
