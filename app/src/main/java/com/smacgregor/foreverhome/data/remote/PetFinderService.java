package com.smacgregor.foreverhome.data.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smacgregor.foreverhome.data.model.Breed;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smacgregor on 3/27/16.
 */
public interface PetFinderService {
    String ENDPOINT = "https://api.petfinder.com/";
    String PETFINDER_KEY = "ff7b9a7b3808a6a3d81d715204763cc1";

    @GET("breed.list")
    Observable<List<Breed>> getBreedList(@Query("animal") String species);

    class Creator {
        public static PetFinderService newPetFinderService() {
            Gson gson = new GsonBuilder().
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
                    registerTypeAdapterFactory(new BreedTypeAdapterFactory()).
                    excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).
                    create();
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(ENDPOINT).
                    addConverterFactory(GsonConverterFactory.create(gson)).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                    client(createHttpClient()).
                    build();
            return retrofit.create(PetFinderService.class);
        }

        /**
         * Create a custom HTTP client with an interceptor that adds the developer key
         * to every request
         * @return
         */
        private static OkHttpClient createHttpClient() {
            OkHttpClient client = new OkHttpClient.Builder().
                    addInterceptor(new Interceptor() {
                                       @Override
                                       public Response intercept(Chain chain) throws IOException {
                                           Request originalRequest = chain.request();
                                           HttpUrl newUrl = originalRequest.url().
                                                   newBuilder().
                                                   addQueryParameter("key", PETFINDER_KEY).
                                                   addQueryParameter("format", "json").
                                                   build();
                                           Request.Builder requestBuilder = originalRequest.newBuilder().
                                                   url(newUrl).
                                                   method(originalRequest.method(), originalRequest.body());
                                           return chain.proceed(requestBuilder.build());
                                       }
                                   }
                    ).
                    build();
            return client;
        }
    }
}
