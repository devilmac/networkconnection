package com.colantoni.federico.networklibrary.retrofit.adapters.simplexml


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import org.simpleframework.xml.Serializer
import retrofit2.Converter
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class SimpleXmlNonStrictConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<Serializer> {

    override fun getConverterAdapterFactory(vararg typeAdapter: Serializer): Converter.Factory? {
        return if (typeAdapter.isNotEmpty()) {
            SimpleXmlConverterFactory.createNonStrict(typeAdapter[0])
        } else SimpleXmlConverterFactory.createNonStrict()
    }

}
