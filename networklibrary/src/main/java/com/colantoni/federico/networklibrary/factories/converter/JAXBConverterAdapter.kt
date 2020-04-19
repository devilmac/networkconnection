package com.colantoni.federico.networklibrary.factories.converter

import retrofit2.Converter
import retrofit2.converter.jaxb.JaxbConverterFactory
import javax.xml.bind.JAXBContext

object JAXBConverterAdapter : ConverterAdapter, ConverterAdapter.WithParameter<JAXBContext> {
    override fun getConverterAdapter(): Converter.Factory = JaxbConverterFactory.create()

    override fun getConverterAdapter(param: JAXBContext): Converter.Factory =
            JaxbConverterFactory.create(param)
}
