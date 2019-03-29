package com.kaushiknsanji.acviewmodel.home;

import android.os.Bundle;

import com.kaushiknsanji.acviewmodel.R;
import com.kaushiknsanji.acviewmodel.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screen_container, new ListFragment())
                    .commit();
        }
    }
}
