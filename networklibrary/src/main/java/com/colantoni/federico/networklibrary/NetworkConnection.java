package com.colantoni.federico.networklibrary;


import com.colantoni.federico.networklibrary.retrofit.RetrofitModule;
import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterType;
import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdaptersRetrofitFactory;
import com.colantoni.federico.networklibrary.retrofit.okhttp.OkHttpModule;

import java.io.Serializable;

import io.reactivex.annotations.Nullable;
import okhttp3.OkHttpClient;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;


/**
 * Singleton class to be used as HTTP request manager.
 */
public final class NetworkConnection implements Serializable {

    /**
     * Global base URL.
     */
    private static String sBaseUrl;

    /**
     * Custom OkHttpClient client
     */
    private static OkHttpClient sOkHttpClient;

    /**
     * Private constructor.
     */
    private NetworkConnection() {

    }

    /**
     * Allows to set a global base URL for the services.
     *
     * @param baseUrl the base URL to use.
     */
    public static void setBaseUrl(final String baseUrl) {

        sBaseUrl = baseUrl;
    }

    /**
     * You can set a custom OkHttpClient to use in place of the default provided by the library.
     *
     * @param okHttpClient custom OkHttpClient.
     */
    public static void setCustomOkHttpClient(OkHttpClient okHttpClient) {

        sOkHttpClient = okHttpClient;
    }

    /**
     * This method initializes an instance of the service you want to use. It's the same {@code initializeServiceInstance()} method but with {@code converterAdapterType} and {@code
     * typeAdapterFactory} as null.
     *
     * @param <S>          Generic type of service class.
     * @param serviceClass The kind of service to be used.
     *
     * @return An initialized instance of the specified service class.
     */
    public static <S> S initializeServiceInstance(final Class<S> serviceClass) {

        return initializeServiceInstance(serviceClass, null, null);
    }

    /**
     * This method initializes an instance of the service you want to use.
     *
     * @param <S>                  Generic type of service class.
     * @param serviceClass         The kind of service to be used.
     * @param converterAdapterType The type of ConverterAdapterFactory to use
     * @param typeAdapterFactory   The typeAdapter to create a ConverterFactory of kind of converterAdapterType
     * @param typeAdapterFactories An array of TypeAdapterFactory to be added to the service; maybe empty.
     *
     * @return An initialized instance of the specified service class.
     */
    @SafeVarargs
    public static <S, T> S initializeServiceInstance(final Class<S> serviceClass, ConverterAdapterType converterAdapterType, final T typeAdapterFactory, final T... typeAdapterFactories) {

        Retrofit.Builder builder = initRetrofitInstance();

        builder = addTypeAdapterFactories(builder, converterAdapterType, typeAdapterFactory, typeAdapterFactories);

        if (sBaseUrl != null) {

            if (sOkHttpClient != null) {

                builder.client(sOkHttpClient);
            }
            else {
                builder.client(OkHttpModule.provideOkHttpClient());
            }

            builder.baseUrl(sBaseUrl);
        }
        else {

            throw new IllegalArgumentException("You have to set a base URL before call this method!");
        }

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    /**
     * @return An instance of Retrofit.
     */
    private static Retrofit.Builder initRetrofitInstance() {

        return RetrofitModule.provideRetrofit();
    }

    /**
     * This method allows you to add to the Retrofit.Builder object an array of TypeAdapterFactory objects.
     *
     * @param builder              The instance of Retrofit.Builder to add adapter factories.
     * @param converterAdapterType The type of ConverterFactory to use.
     * @param typeAdapterFactories An array of factory adapters; maybe empty.
     *
     * @return The same instance of Retrofit.Builder passed as parameter.
     */
    @SafeVarargs
    private static <T> Retrofit.Builder addTypeAdapterFactories(final Retrofit.Builder builder, @Nullable ConverterAdapterType converterAdapterType, final T typeAdapterFactory, final T...
    typeAdapterFactories) {

        if (converterAdapterType == null) {
            return builder;
        }

        ConverterAdaptersRetrofitFactory<T> converterAdaptersRetrofitFactory = new ConverterAdaptersRetrofitFactory<>(typeAdapterFactory, typeAdapterFactories);
        Factory factory = converterAdaptersRetrofitFactory.getTypeAdapterRetrofit(converterAdapterType);

        if (factory != null) {
            builder.addConverterFactory(factory);
        }

        return builder;
    }
}
