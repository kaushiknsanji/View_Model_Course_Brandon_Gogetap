package com.kaushiknsanji.acviewmodel.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Annotation that identifies the Key for the Provider Map Entry.
 *
 * @author Kaushik N Sanji
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@MapKey
public @interface ViewModelKey {
    //Accepts a ViewModel class as the Key
    Class<? extends ViewModel> value();
}