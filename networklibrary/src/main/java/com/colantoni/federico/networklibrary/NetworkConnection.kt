package com.colantoni.federico.networklibrary


import okhttp3.OkHttpClient
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.IllegalArgumentException


/**
 * Singleton class to be used as HTTP request manager.
 */
class NetworkConnection private constructor(builder: NetworkConnectionBuilder) {

    private var baseUrl: String? = null
    private var okHttpClient: OkHttpClient? = null
    private var factory: Factory? = null
    private var isRxAdapter: Boolean = false

    /**
     * This method initializes an instance of the service you want to use.
     *
     * @param serviceClass The kind of service to be used.
     *
     * @return An initialized instance of the specified service class.
    </S> */
    fun <S> initializeServiceInstance(serviceClass: Class<S>): S {
        val builder = initRetrofitInstance()

        builder.addConverterFactory(factory!!)

        if (baseUrl != null) {

            if (okHttpClient != null) {

                builder.client(okHttpClient!!)
            }

            builder.baseUrl(baseUrl!!)
        } else {
            throw IllegalArgumentException("You have to set a base URL before call this method!")
        }

        val retrofit = builder.build()

        return retrofit.create(serviceClass)
    }

    private fun initRetrofitInstance(): Retrofit.Builder {

        val builder = Retrofit.Builder()

        return if (isRxAdapter)
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        else
            builder
    }

    /** Builder class to create step by step a [T] service class for a REST API manager. */
    inner class NetworkConnectionBuilder(val baseUrl: String) {

        internal var client: OkHttpClient? = null
        internal var factory: Factory? = null
        internal var isRxAdapter: Boolean = false

        /** Set a custom [OkHttpClient] object */
        fun okHttpClient(client: OkHttpClient): NetworkConnectionBuilder {
            this.client = client
            return this
        }

        /** Set a converter factory object to manage REST API response objects. */
        fun converterFactory(factory: Factory): NetworkConnectionBuilder {
            this.factory = factory
            return this
        }

        /** Speficy if the [T] service needs a RxJava adapter */
        fun rxAdapter(isRxAdapter: Boolean): NetworkConnectionBuilder {
            this.isRxAdapter = isRxAdapter
            return this
        }

        /** Returns a [T] service class that represents a REST API manager. */
        fun <T> build(serviceClass: Class<T>): T {
            return initializeServiceInstance(serviceClass)
        }
    }

    init {
        baseUrl = builder.baseUrl
        okHttpClient = builder.client
        factory = builder.factory
        isRxAdapter = builder.isRxAdapter
    }
}
