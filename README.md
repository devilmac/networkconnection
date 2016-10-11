**NetworkConnection**
[![Release](https://jitpack.io/v/devilmac/networkconnection.svg)](https://jitpack.io/#devilmac/networkconnection)

This library is a simple base network connection based on Retrofit.
NetworkConnection also supports RxJava to make HTTP requests.

If you want to use NetworkConnection in your project, you have to do the following steps:

* Add Jitpack.io to your root build.gradle file:

	   `allprojects {`
            `repositories {`
			     `maven { url "https://jitpack.io" }`
			`}`
	   `}`

* Add the gradle dependency to your app build.gradle file:

    `dependencies {`
        `compile 'com.github.devilmac:networkconnection:libVersion'`
    `}`

    where `libVersion` is the latest version of the library.

**How to use**

Usage is very simple; all you have to do is the following:  
* call `setBaseURL()` method to set the base URL of your services  
* get an instance of NetworkConnection class like this  
        `NetworkConnection netConn = NetworkConnection.getinstance();`  
* then you have to initialize the Retrofit instance:  
        `YourService service = networkConnection.initializeServiceInstance(this, YourService.class, TypeAdapterFactory... typeAdapterFactories);`  
* finally, you can make HTTP requests using your service! You can use the standard Retrofit Call<T> class or the Observable<T> class of RxJava. Is up to you which method fits better your needs.

*N.B.*: `typeAdapterFactories` is used to pass to Retrofit instance of the library an array of Gson TypeAdapterFactory, if you have your response classes mapped in some way different from standard Gson serialization, like Jackson, Moshi etc., as specified here in Retrofit documentation [Custom converters](http://square.github.io/retrofit/#restadapter-configuration). Check the sample app for an example of usage of custom TypeAdapteractory adapters, in particular it was used Immutables library; you can find a detailed guide here [How to use Immutables with Retrofit in Android](https://medium.com/@fedecola/how-to-use-immutables-with-retrofit-in-android-dde4237deb4f).
