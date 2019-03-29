package com.kaushiknsanji.acviewmodel.base;

import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

/**
 * An Abstract {@link Fragment} class which is the base class
 * for all the Fragments in this application.
 */
public abstract class BaseFragment extends Fragment {

    //Tracks if the ApplicationComponent is used more than once in the same Fragment to inject services
    private boolean mIsComponentUsed;

    /**
     * Method that returns an {@link ApplicationComponent} instance.
     *
     * @return An {@link ApplicationComponent} instance
     */
    @UiThread
    protected ApplicationComponent getApplicationComponent() {
        if (mIsComponentUsed) {
            //Throwing exception when invoked more than once in the same Fragment
            throw new RuntimeException("No need to use ApplicationComponent more than once");
        }
        //Mark as used
        mIsComponentUsed = true;
        //Returning the instance
        return ((MyApplication) requireActivity().getApplication()).getApplicationComponent();
    }

}
