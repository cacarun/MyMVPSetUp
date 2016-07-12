package com.android.mvp2.http.manager;

import com.android.mvp2.BuildConfig;
import com.android.mvp2.http.JSONConverterFactory;
import com.android.mvp2.http.api.MainAPI;
import com.cjw.util.CusLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by cjw on 2016/7/4.
 */
public class HttpClientManager {

    private static final String TAG = "HttpClient";

    private static Retrofit retrofit;

    private static MainAPI mainAPI;

    /**
     * **************** Http Config ******************
     */
    private static final String BASE_URL = "http://api.haobangshou.me/";

    // 读取超时时间
    private static final int READ_TIMEOUT = 10 * 1000;

    // 连接超时时间
    private static final int CONNECT_TIMEOUT = 5 * 1000;

    /**
     * ************************ Single instance **************************
     */
    private static HttpClientManager instance;

    public synchronized static HttpClientManager getInstance() {
        if (instance == null) {
            instance = new HttpClientManager();
        }

        return instance;
    }

    private HttpClientManager() {
        init();
    }

    private void init() {

        if (retrofit == null) {

            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // e.g. headers.put("User-agent", "e bang shou/" + DeviceUtil.getAppVersion() + " (Android " + android.os.Build.VERSION.RELEASE + ")");
                    Request request = original.newBuilder()
                            .header("User-Agent", "Your-App-Name")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            };


            OkHttpClient okHttpClient;

            if (BuildConfig.DEBUG) {

                CusLog.d("Debug interceptor Log init in HttpClient.");

                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                        .addInterceptor(interceptor)
                        .addInterceptor(httpLoggingInterceptor)
                        .build();
            } else {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                        .addInterceptor(interceptor)
                        .build();
            }

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(JSONConverterFactory.create())
                    .build();
        }
    }


    /**
     * ************************ Call API **************************
     */

    public MainAPI getMainAPI() {
        if (mainAPI == null) {
            mainAPI = retrofit.create(MainAPI.class);
        }

        return mainAPI;
    }
}
