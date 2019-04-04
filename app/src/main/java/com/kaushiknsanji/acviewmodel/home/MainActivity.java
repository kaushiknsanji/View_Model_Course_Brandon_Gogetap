package com.kaushiknsanji.acviewmodel.home;

import android.os.Bundle;

import com.kaushiknsanji.acviewmodel.R;
import com.kaushiknsanji.acviewmodel.base.BaseActivity;

/**
 * The Main Activity of the App that inflates the layout 'R.layout.activity_main'
 * and displays the {@link ListFragment} on load at its container 'R.id.screen_container'.
 *
 * @author Kaushik N Sanji
 */
public class MainActivity extends BaseActivity {

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load the Activity layout
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //On Initial launch, load the Repository list fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screen_container, new ListFragment())
                    .commit();
        }
    }
}
