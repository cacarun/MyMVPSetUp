package com.android.mvp2.exception.manager;

import com.android.mvp2.constant.ErrorCode;
import com.android.mvp2.exception.APIException;
import com.android.mvp2.exception.HttpResultException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

public class ExceptionManager {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static APIException handleException(Throwable e) {
        APIException ex;
        if (e instanceof HttpException) {             // HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new APIException(e, ErrorCode.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("网络错误");  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof HttpResultException) {    //服务器返回的错误
            HttpResultException resultException = (HttpResultException) e;
            ex = new APIException(resultException, resultException.getCode());
            ex.setDisplayMessage(resultException.getMsg());
            return ex;
        } else if (e instanceof JSONException || e instanceof ParseException) {
            ex = new APIException(e, ErrorCode.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new APIException(e, ErrorCode.NETWORK_ERROR);
            ex.setDisplayMessage("连接失败");  //均视为网络错误
            return ex;
        } else {
            ex = new APIException(e, ErrorCode.UNKNOWN);
            ex.setDisplayMessage("未知错误");          //未知错误
            return ex;
        }
    }
}
