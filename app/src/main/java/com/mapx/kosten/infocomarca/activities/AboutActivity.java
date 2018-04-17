package com.mapx.kosten.infocomarca.activities;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.mapx.kosten.infocomarca.R;

public class AboutActivity extends AppCompatActivity {

    // declaration of variables
    private Toolbar mToolbar;
    private TextView mVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolbarLayout_main);
        mVersionName = (TextView) findViewById(R.id.txtView_appVersion);

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("infoPatagones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            int vcode = pInfo.versionCode;
            mVersionName.setText("V " +String.valueOf(vcode) + "." + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    // funcion Menu para boton navigation back
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
