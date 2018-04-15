package com.mapx.kosten.infocomarca.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;;
import android.view.MenuItem;

import com.mapx.kosten.infocomarca.R;

public class NewspaperActivity extends AppCompatActivity {

    private final String TAG = "NewspaperActivity";

    // declaration of variables
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspaper);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolBarLayout_main);

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Diarios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    // listener de los iconos del menu de la toolbar
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
