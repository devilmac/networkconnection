package com.colantoni.federico.networklibrary.factories.converter

import org.simpleframework.xml.Serializer
import retrofit2.Converter
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Deprecated("This converter was deprecated.",
        replaceWith = ReplaceWith("Is recommended to switch to JAXB implementation."))
object SimpleXmlConverterAdapter : ConverterAdapter, ConverterAdapter.WithParameter<Serializer> {
    override fun getConverterAdapter(): Converter.Factory = SimpleXmlConverterFactory.create()

    override fun getConverterAdapter(param: Serializer): Converter.Factory =
            SimpleXmlConverterFactory.create(param)
}
