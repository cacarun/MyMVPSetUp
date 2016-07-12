package com.android.mvp2.http.api;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface MainAPI {

    @FormUrlEncoded
    @POST("account/login")
    Observable<JSONObject> login(@FieldMap Map<String, String> map);


    // e.g.
    /**
     http://square.github.io/retrofit/
     */
}