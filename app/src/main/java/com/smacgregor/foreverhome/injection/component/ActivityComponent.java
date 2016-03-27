package com.smacgregor.foreverhome.injection.component;

import dagger.Component;
import com.smacgregor.foreverhome.injection.PerActivity;
import com.smacgregor.foreverhome.injection.module.ActivityModule;
import com.smacgregor.foreverhome.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
