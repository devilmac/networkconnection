* Version 0.2.2:
    + setBaseUrl() moved as class level method.
  
* Version 0.2.0:
    + Added setBaseUrl() method to set the base URL; if you call initializeServiceInstance() method before setting the base URL it will be thrown an exception.
    + initializeServiceInstance() doesn't take the base URL parameter anymore.
  
* Version 0.1.0:
    + Added support for Immutables library with basic interface BaseResponse.java.
    + Modified NetworkConnection class to have a better singleton management.
    + Refactor of NetworkConnection.java class to add support to Immutables and related Gson adapters.
  
* Version 0.0.2:
    + Minor changes.
  
* Version 0.0.1:
    + Initial version of the library that contains very basic code to make HTTP requests through Retrofit.
    + Support for RxJava.
