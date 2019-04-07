package com.kaushiknsanji.acviewmodel.model;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Abstract Class that implements {@link JsonAdapter.Factory} to provide
 * appropriate JsonAdapters for use with {@link com.squareup.moshi.Moshi}
 * de/serialization of JSON, for all AutoValue Moshi instances.
 *
 * @author Kaushik N Sanji
 */
@MoshiAdapterFactory
public abstract class ModelAdapterFactory implements JsonAdapter.Factory {

    /**
     * Factory Method of {@link ModelAdapterFactory} that returns {@link JsonAdapter.Factory}
     * for use with {@link com.squareup.moshi.Moshi} de/serialization of JSON.
     *
     * @return Instance of {@link JsonAdapter.Factory}
     */
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_ModelAdapterFactory();
    }
}