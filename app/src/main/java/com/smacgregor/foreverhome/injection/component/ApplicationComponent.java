package com.smacgregor.foreverhome.injection.component;

import android.app.Application;
import android.content.Context;

import com.smacgregor.foreverhome.data.DataManager;
import com.smacgregor.foreverhome.data.SyncService;
import com.smacgregor.foreverhome.data.local.DatabaseHelper;
import com.smacgregor.foreverhome.data.local.PreferencesHelper;
import com.smacgregor.foreverhome.data.remote.PetFinderService;
import com.smacgregor.foreverhome.data.remote.RibotsService;
import com.smacgregor.foreverhome.injection.ApplicationContext;
import com.smacgregor.foreverhome.injection.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    PetFinderService petFinderService();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();
}
