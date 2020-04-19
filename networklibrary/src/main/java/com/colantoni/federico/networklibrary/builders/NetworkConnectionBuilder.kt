package com.colantoni.federico.networklibrary.builders

import com.colantoni.federico.networklibrary.NetworkConnection
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

class NetworkConnectionBuilder : Builder {
    private var networkConnection = NetworkConnection()

    override fun reset() {
        networkConnection = NetworkConnection()
    }

    override fun buildUrlConnectionString(connectionString: String): Builder {
        networkConnection.setServiceBaseUrl(connectionString)
        return this
    }

    override fun buildOkHttpClient(client: OkHttpClient): Builder {
        networkConnection.setOkHttpClient(client)
        return this
    }

    override fun buildCallAdapterFactory(adapter: CallAdapter.Factory?): Builder {
        adapter?.let { networkConnection.setCallAdapter(it) }
        return this
    }

    override fun buildConverterAdapterFactory(converter: Converter.Factory?): Builder {
        converter?.let { networkConnection.setConverterAdapter(it) }
        return this
    }

    override fun build(): NetworkConnection = networkConnection
}
