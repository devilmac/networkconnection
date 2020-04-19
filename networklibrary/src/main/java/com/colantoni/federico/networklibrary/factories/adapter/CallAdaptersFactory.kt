package com.colantoni.federico.networklibrary.factories.adapter

import com.colantoni.federico.networklibrary.factories.adapter.CallAdaptersEnum.GUAVA_LISTENABLE
import com.colantoni.federico.networklibrary.factories.adapter.CallAdaptersEnum.RXJAVA
import com.colantoni.federico.networklibrary.factories.adapter.CallAdaptersEnum.RXJAVA2
import retrofit2.CallAdapter

class CallAdaptersFactory {
    fun getCallAdapterFactory(adapterType: CallAdaptersEnum): CallAdapter.Factory =
            when (adapterType) {
                RXJAVA -> RxJavaCallAdapter.getCallAdapter()
                GUAVA_LISTENABLE -> GuavaListenableFutureCallAdapter.getCallAdapter()
                RXJAVA2 -> RxJava2CallAdapter.getCallAdapter()
            }
}
