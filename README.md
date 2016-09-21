**NetworkConnection**
[![Release](https://jitpack.io/v/devilmac/networkconnection.svg)](https://jitpack.io/#devilmac/networkconnection)

This library is a simple base network connection based on Retrofit.
NetworkConnection also supports RxJava to make HTTP requests.

If you want to use NetworkConnection in your project, you have to do the following steps:

* Add Jitpack.io to your root build.gradle file:

	   `allprojects {
	        repositories {
			    maven { url "https://jitpack.io" }
		    }
	    }`

* Add the gradle dependency to your app build.gradle file:

    `dependencies {
        compile 'com.github.devilmac:networkconnection:libVersion'
    }`

    where `libVersion` is the latest version of the library.

**How to use**

Usage is very simple; all you have to do is the following:
    • get an instance of NetworkConnection class like this
        `NetworkConnection netConn=NetworkConnection.getinstance();`
    • then you have to initialize the Retrofit instance:
        `YourService service = networkConnection.initializeRetrofitInstance(this, "http://baseUrlOfTheService", YourService.class);`
    • finally, you can make HTTP requests using your service! You can use the standard Retrofit Call<T> class or the Observable<T> class of RxJava. Is up to you which method fits better your needs.

**TO DO**
    • TBD
