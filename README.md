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

Usage is very simple. All you have to do is the following:
* call `setBaseURL()` method to set the base URL of your services (If you are an Android developer, I recommend to call this method in your custom application or in your main activity)
* call `setCustomOkHttpClient()` method to use your own custom client instead of the default provided by the library
* it's not longer needed to get an instance of NetworkConnection class, you can simply call `NetworkConnection.initializeServiceInstance()` method create an instance of the service you want to use:
        `YourService service = NetworkConnection.initializeServiceInstance(this, YourService.class);`
* if you have to add some JSON adapters different from the standard GSON serialization, like Jackson, Moshi, etc., you have to add them to the method above this way:
        `YourService service = NetworkConnection.initializeServiceInstance(this, YourService.class, TypeAdapterFactory... typeAdapterFactories);`
Check the Retrofit documentation for more details [Custom converters](http://square.github.io/retrofit/#restadapter-configuration).
* finally, you can make HTTP requests using your service! In your service you can use the standard Retrofit Call<T> class or the Observable<T> class of RxJava. Is up to you choose which method fits better your needs.

*N.B.*: Check the sample app for an example of usage of custom TypeAdapteractory adapters, in particular it was used Immutables library; you can find a detailed guide here [How to use Immutables with Retrofit in Android](https://medium.com/@fedecola/how-to-use-immutables-with-retrofit-in-android-dde4237deb4f).
