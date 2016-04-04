package com.smacgregor.foreverhome.data;

import com.smacgregor.foreverhome.data.local.DatabaseHelper;
import com.smacgregor.foreverhome.data.local.PreferencesHelper;
import com.smacgregor.foreverhome.data.model.Breed;
import com.smacgregor.foreverhome.data.model.Pet;
import com.smacgregor.foreverhome.data.model.Ribot;
import com.smacgregor.foreverhome.data.remote.PetFinderService;
import com.smacgregor.foreverhome.data.remote.RibotsService;
import com.smacgregor.foreverhome.util.EventPosterHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final PetFinderService mPetFinderService;
    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(PetFinderService petFinderService, RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mPetFinderService = petFinderService;
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<List<Breed>> syncBreeds() {
        return mPetFinderService.getBreedList("dog");
    }

    public Observable<List<Pet>> findPets() {
        return mPetFinderService.searchForPets("94116", null, "dog");
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
