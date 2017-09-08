package com.colantoni.federico.networklibrary.retrofit.adapters


import io.reactivex.annotations.Nullable
import retrofit2.Converter.Factory


/**
 * This interface specifies a base converter adapter strategy of generic type
 */
interface ConverterAdapterRetrofitStrategy<in T> {

    /** Return a {@code Factory} object based on strategy used.
     *
     * @param typeAdapter Generic type adapter to use when creates the {@code Factory}
     *
     * @return {@code Factory} object.
     * */
    fun getConverterAdapterFactory(@Nullable vararg typeAdapter: T): Factory?
}
