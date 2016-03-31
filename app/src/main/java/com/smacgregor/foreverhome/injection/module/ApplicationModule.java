package com.smacgregor.foreverhome.injection.module;

import android.app.Application;
import android.content.Context;

import com.smacgregor.foreverhome.data.remote.PetFinderService;
import com.smacgregor.foreverhome.data.remote.RibotsService;
import com.smacgregor.foreverhome.injection.ApplicationContext;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    PetFinderService providePetFinderService() {
        return PetFinderService.Creator.newPetFinderService();
    }
}
