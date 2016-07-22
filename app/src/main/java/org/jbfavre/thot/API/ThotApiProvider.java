package org.jbfavre.thot.API;

import android.content.Context;
import android.content.SharedPreferences;

import org.jbfavre.thot.helpers.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThotApiProvider {

    static public SelfossApi selfossApi;

    public ThotApiProvider(final Context context){
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Cookie", SharedPreferencesHelper.getToken(context)).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SharedPreferencesHelper.getEndpoint(context)).client(httpClient).build();

        selfossApi = retrofit.create(SelfossApi.class);
    }

}
