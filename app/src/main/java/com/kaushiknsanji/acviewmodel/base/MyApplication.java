package com.kaushiknsanji.acviewmodel.base;

import android.app.Application;

/**
 * {@link Application} class for exposing {@link ApplicationComponent}.
 *
 * @author Kaushik N Sanji
 */
public class MyApplication extends Application {

    //Dagger Component for exposing instances of services tied to the Application Lifecycle
    private ApplicationComponent mApplicationComponent;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing the ApplicationComponent
        mApplicationComponent = DaggerApplicationComponent.create();
    }

    /**
     * Getter Method for the {@link ApplicationComponent} instance
     *
     * @return Instance of {@link ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
