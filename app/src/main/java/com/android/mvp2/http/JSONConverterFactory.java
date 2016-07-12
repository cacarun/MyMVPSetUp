package com.android.mvp2.http;

import com.android.mvp2.constant.ErrorCode;
import com.android.mvp2.exception.HttpResultException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by cjw on 2016/7/6.
 */
public class JSONConverterFactory extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    public static JSONConverterFactory create() {
        return new JSONConverterFactory();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        if (JSONObject.class.equals(type)) {

            return new Converter<JSONObject, RequestBody>() {

                @Override
                public RequestBody convert(JSONObject value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value.toString());
                }
            };
        }

        return null;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if (JSONObject.class.equals(type)) {

            return new Converter<ResponseBody, JSONObject>() {

                @Override
                public JSONObject convert(ResponseBody value) throws IOException {
                    try {
                        JSONObject jsonObj = new JSONObject(value.string());
                        return jsonObj;
                    } catch (JSONException e) {
                        throw new HttpResultException(ErrorCode.PARSE_ERROR, e.getMessage());
                    }
                }
            };
        }

        return null;
    }
}
