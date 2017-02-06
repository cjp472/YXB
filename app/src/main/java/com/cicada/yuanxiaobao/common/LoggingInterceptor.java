package com.cicada.yuanxiaobao.common;

import android.util.Log;

import com.cicada.yuanxiaobao.BuildConfig;
import com.cicada.yuanxiaobao.utils.AppSelectSP;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * https://packages-gitlab-com.s3.amazonaws.com/7/8/el/6/package_files/666.rpm?AWSAccessKeyId=AKIAJ74R7IHMTQVGFCEA&Signature=ySPwdqUxd1Tg7M2LafxEbazc0WA%3D&Expires=1445655058
 * Print log in Logcat
 */
public class LoggingInterceptor implements Interceptor {

    private boolean isToken;

    public LoggingInterceptor(boolean isToken) {
        this.isToken = isToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        String url = request.url().toString();
        int last = url.lastIndexOf("/");
        url = url.substring(0, last);
        if (!isToken) {
            newBuilder.url(url);
        } else {
            url=url + "?token=" + AppSelectSP.getString("token");
            newBuilder.url(url);
        }

        long t1 = System.nanoTime();
        String requestLog = String.format("请求:  %s ", url, chain.connection());

        if (BuildConfig.DEBUG) {
            Log.d("-- Retrofit --", requestLog);
            Log.d("-- Retrofit --", "请求参数:\n"+bodyToString(request));
        }

        Response response = chain.proceed(newBuilder.build());
        long t2 = System.nanoTime();
        String responseLog = String.format("返回: for %s  耗时: %.1fms ", response.request().url(), (t2 - t1) / 1e6d);
        String bodyString = response.body().string();
        if (BuildConfig.DEBUG) {
            Log.d("-- Retrofit --", responseLog);
            Log.d("-- Retrofit --", "返回值:\n"+bodyString+"\n  ");
        }
        return chain.proceed(newBuilder.build());

    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            String string=buffer.readUtf8();
//            RequestModel model= BasePresenter.gson.fromJson(string,new TypeToken<RequestModel>(){}.getType());
//            String data=model.getData().toString();
            return string;
        } catch (final IOException e) {
            return "did not work";
        }
    }
}

