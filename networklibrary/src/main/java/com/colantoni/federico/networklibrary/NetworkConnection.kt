package com.colantoni.federico.networklibrary

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Singleton class to be used as HTTP request manager.
 */
class NetworkConnection {
    private val retrofitBuilder = Retrofit.Builder()

    fun setServiceBaseUrl(baseUrl: String) {
        retrofitBuilder.baseUrl(baseUrl)
    }

    fun setOkHttpClient(okHttpClient: OkHttpClient) {
        retrofitBuilder.client(okHttpClient)
    }

    fun setCallAdapter(callAdapter: CallAdapter.Factory) {
        retrofitBuilder.addCallAdapterFactory(callAdapter)
    }

    fun setConverterAdapter(converterAdapter: Converter.Factory) {
        retrofitBuilder.addConverterFactory(converterAdapter)
    }

    /**
     * This method initializes an instance of the service you want to use.
     *
     * @param serviceClass The kind of service to be used.
     *
     * @return An initialized instance of the specified service class.
    </S> */
    fun <S> initServiceInstance(serviceClass: Class<S>): S {
        val retrofit = retrofitBuilder.build()
        return retrofit.create(serviceClass)
    }
}
