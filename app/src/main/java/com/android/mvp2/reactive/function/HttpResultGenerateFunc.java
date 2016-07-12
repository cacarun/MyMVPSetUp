package com.android.mvp2.reactive.function;

import com.android.mvp2.constant.ErrorCode;
import com.android.mvp2.exception.HttpResultException;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Func1;

/**
 * Created by cjw on 2016/6/29.
 */

public abstract class HttpResultGenerateFunc<T> implements Func1<JSONObject, T> {

    public abstract T generateModelFromJSONObject(JSONObject dataObj) throws JSONException;

    @Override
    public T call(JSONObject httpResult) {

        try {

            if (httpResult != null) {

                if (httpResult.has("error_code")) {

                    if (httpResult.getInt("error_code") != 0) {
                        // 服务器返回 error msg
                        if (httpResult.has("error_str")) {
                            throw new HttpResultException(httpResult.getInt("error_code"), httpResult.getString("error_str"));
                        } else {
                            throw new JSONException("error_str not existed");
                        }
                    }

                    if (!httpResult.has("data")) {
                        throw new JSONException("data not existed");
                    }

                    return generateModelFromJSONObject(httpResult.getJSONObject("data"));

                } else {
                    throw new JSONException("error_code not existed");
                }
            } else {
                throw new JSONException("httpResult not existed");
            }

        } catch (JSONException e) {
            throw new HttpResultException(ErrorCode.PARSE_ERROR, e.getMessage());
        }
    }

}
