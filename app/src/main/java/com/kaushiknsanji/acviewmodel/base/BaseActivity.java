package com.kaushiknsanji.acviewmodel.base;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

/**
 * An Abstract {@link AppCompatActivity} class which is the base class
 * for all the activities in this application.
 *
 * @author Kaushik N Sanji
 */
public abstract class BaseActivity extends AppCompatActivity {

    //Tracks if the ApplicationComponent is used more than once in the same Activity to inject services
    private boolean mIsComponentUsed;

    /**
     * Method that returns an {@link ApplicationComponent} instance.
     *
     * @return An {@link ApplicationComponent} instance
     */
    @UiThread
    protected ApplicationComponent getApplicationComponent() {
        if (mIsComponentUsed) {
            //Throwing exception when invoked more than once in the same Activity
            throw new RuntimeException("No need to use ApplicationComponent more than once");
        }
        //Mark as used
        mIsComponentUsed = true;
        //Returning the instance
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}