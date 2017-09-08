package com.colantoni.federico.networklibrary.core;


import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Builder class to be used to create a HTTP REST API manager.
 */
public class NetworkConnection {

    private String baseUrl;

    private OkHttpClient okHttpClient;

    private Converter.Factory factory;

    private boolean isRxAdapter;

    NetworkConnection(NetworkConnectionBuilder builder) {

        baseUrl = builder.baseUrl;
        okHttpClient = builder.okHttpClient;
        factory = builder.factory;
        isRxAdapter = builder.isRxAdapter;
    }

    /**
     * This method initializes an instance of the service you want to use.
     *
     * @param serviceClass The kind of service to be used.
     *
     * @return An initialized instance of the specified service class.
     * </S>
     */
    public <S> S initializeServiceInstance(Class<S> serviceClass) {

        Retrofit.Builder builder = initRetrofitInstance();

        if (factory != null) {
            builder.addConverterFactory(factory);
        }

        if (baseUrl != null) {

            if (okHttpClient != null) {
                builder.client(okHttpClient);
            }

            builder.baseUrl(baseUrl);
        }
        else {
            throw new IllegalArgumentException("You have to set a base URL before call this method!");
        }

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    private Retrofit.Builder initRetrofitInstance() {

        Retrofit.Builder builder = new Retrofit.Builder();

        if (isRxAdapter) {
            return builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        else {
            return builder;
        }
    }

    public static class NetworkConnectionBuilder {

        String baseUrl;

        OkHttpClient okHttpClient;

        Converter.Factory factory;

        boolean isRxAdapter;

        public NetworkConnectionBuilder(String baseUrl) {

            this.baseUrl = baseUrl;
        }

        /**
         * Set a custom [OkHttpClient] object
         */
        public NetworkConnectionBuilder okHttpClient(OkHttpClient client) {

            this.okHttpClient = client;
            return this;
        }

        /**
         * Set a converter factory object to manage REST API response objects.
         */
        public NetworkConnectionBuilder converterFactory(Converter.Factory factory) {

            this.factory = factory;
            return this;
        }

        /**
         * Speficy if the [T] service needs a RxJava adapter
         */
        public NetworkConnectionBuilder rxAdapter(boolean isRxAdapter) {

            this.isRxAdapter = isRxAdapter;
            return this;
        }

        /**
         * Returns a [T] service class that represents a REST API manager.
         */
        public NetworkConnection build() {

            return new NetworkConnection(this);
        }
    }
}
