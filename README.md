NetworkConnection [![Release](https://jitpack.io/v/User/Repo.svg)](https://jitpack.io/#devilmac/networkconnection)

This library is a simple base network connection based on Retrofit.
NetworkConnection also supports RxJava to make HTTP requests.

If you want to use NetworkConnection in your project, you have to do the following steps:

* Add Jitpack.io to your root build.gradle file:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

* Add the gradle dependency to your app build.gradle file:

    dependencies {
        compile 'com.github.devilmac:networkconnection:<libVersion>'
    }

    where <libVersion> is the latest version of the library.
