package com.colantoni.federico.networklibrary;


import android.content.Context;

import com.colantoni.federico.networklibrary.retrofit.RetrofitModule;
import com.colantoni.federico.networklibrary.retrofit.okhttp.OkHttpModule;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Singleton class to be used as HTTP request manager.
 */
public final class NetworkConnection implements Serializable {

    /**
     * Instance of NetworkConnection class.
     */
    private static volatile NetworkConnection instance = null;

    /**
     * Global base URL.
     */
    private static String sBaseUrl;

    /**
     * Private constructor.
     */
    private NetworkConnection() {

    }

    /**
     * @return A singleton instance of NetworkConnection.
     */
    public static NetworkConnection getInstance() {

        if (instance == null) {

            synchronized (NetworkConnection.class) {

                if (instance == null) {

                    instance = new NetworkConnection();
                }
            }
        }

        return instance;
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
     * This method initializes an instance of the service you want to use.
     *
     * @param context              The app/activity {@link Context} used.
     * @param serviceClass         The kind of service to be used.
     * @param typeAdapterFactories An array of TypeAdapterFactory to be added to the service; maybe empty.
     * @param <S>                  Generic type of service class.
     *
     * @return An initialized instance of the specified service class.
     */
    public <S> S initializeServiceInstance(final Context context, final Class<S> serviceClass, final TypeAdapterFactory... typeAdapterFactories) {

        Retrofit.Builder builder = initRetrofitInstance();

        builder = addTypeAdapterFactories(builder, typeAdapterFactories);

        if (sBaseUrl != null) {

            builder.client(OkHttpModule.provideOkHttpClient(context)).baseUrl(sBaseUrl);
        } else {

            throw new IllegalArgumentException("You have to set a base URL before call this method!");
        }

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    /**
     * @return An instance of Retrofit.
     */
    private Retrofit.Builder initRetrofitInstance() {

        return RetrofitModule.provideRetrofit();
    }

    /**
     * This method allows you to add to the Retrofit.Builder object an array of TypeAdapterFactory objects.
     *
     * @param builder              The instance of Retrofit.Builder to add adapter factories.
     * @param typeAdapterFactories An array of TypeAdapteractory; maybe empty.
     *
     * @return The same instance of Retrofit.Builder passed as parameter.
     */
    private Retrofit.Builder addTypeAdapterFactories(final Retrofit.Builder builder, final TypeAdapterFactory... typeAdapterFactories) {

        if (typeAdapterFactories.length > 0) {

            for (TypeAdapterFactory factory : typeAdapterFactories) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapterFactory(factory);

                GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());

                builder.addConverterFactory(gsonConverterFactory);
            }
        } else {

            builder.addConverterFactory(GsonConverterFactory.create());
        }

        return builder;
    }
}
