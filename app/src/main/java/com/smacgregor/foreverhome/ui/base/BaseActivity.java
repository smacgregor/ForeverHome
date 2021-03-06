package com.smacgregor.foreverhome.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smacgregor.foreverhome.ForeverHomeApplication;
import com.smacgregor.foreverhome.injection.component.ActivityComponent;
import com.smacgregor.foreverhome.injection.component.DaggerActivityComponent;
import com.smacgregor.foreverhome.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(ForeverHomeApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
