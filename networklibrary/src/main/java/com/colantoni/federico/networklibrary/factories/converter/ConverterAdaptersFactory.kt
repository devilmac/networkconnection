package com.colantoni.federico.networklibrary.factories.converter

import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.JACKSON
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.JAXB
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.MOSHI
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.SCALARS
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.SIMPLE_XML
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum.WIRE
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import org.simpleframework.xml.Serializer
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.xml.bind.JAXBContext

class ConverterAdaptersFactory {
    fun getConverterAdapterFactory(adapterType: ConverterAdaptersEnum): Converter.Factory =
            when {
                adapterType == JACKSON -> JacksonConverterAdapter.getConverterAdapter()
                adapterType == MOSHI -> MoshiConverterAdapter.getConverterAdapter()
                adapterType == WIRE -> WireConverterAdapter.getConverterAdapter()
                adapterType == SIMPLE_XML -> SimpleXmlConverterAdapter.getConverterAdapter()
                adapterType == JAXB -> JAXBConverterAdapter.getConverterAdapter()
                adapterType == SCALARS -> ScalarsConverterAdapter.getConverterAdapter()
                else -> GsonConverterFactory.create()
            }

    fun getConverterAdapterFactory(adapterType: ConverterAdaptersEnum, param: Any): Converter.Factory =
            when {
                adapterType == JACKSON -> JacksonConverterAdapter.getConverterAdapter(param as ObjectMapper)
                adapterType == MOSHI -> MoshiConverterAdapter.getConverterAdapter(param as Moshi)
                adapterType == WIRE -> throw IllegalArgumentException("Wire adapter cannot be created with a specialized parameter.")
                adapterType == SIMPLE_XML -> SimpleXmlConverterAdapter.getConverterAdapter(param as Serializer)
                adapterType == JAXB -> JAXBConverterAdapter.getConverterAdapter(param as JAXBContext)
                adapterType == SCALARS -> throw IllegalArgumentException("Scalars adapter cannot be created with a specialized parameter.")
                else -> GsonConverterFactory.create(param as Gson)
            }
}
