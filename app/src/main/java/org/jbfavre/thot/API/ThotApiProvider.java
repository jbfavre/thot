package org.jbfavre.thot.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThotApiProvider {

    static public SelfossApi selfossApi;

    public ThotApiProvider(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.213.38:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        selfossApi = retrofit.create(SelfossApi.class);
    }

}
