package com.cicada.yuanxiaobao.common;

import android.text.TextUtils;
import android.widget.Toast;

import com.cicada.yuanxiaobao.API.YXBAPI;
import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.utils.AppIDUSP;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.utils.NetworkUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * AUTHOR: TL
 * DATE: 21/10/2015 19:09
 */
public abstract class ApiDataFactory {

    public static final String REQUEST_SUCCESS = "0000000";//请求数据成功

    public static OkHttpClient client(boolean isToken) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor(isToken))
                .build();
        return client;
    }

    public static YXBAPI getService(String url,boolean isToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client(isToken))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        YXBAPI service = retrofit.create(YXBAPI.class);
        return service;
    }

    /**
     * 接口调用
     *
     * @param url   方法名
     * @param param 参数
     * @param  isToken 接口是否带token
     */
    public static void serviceAPI(final String url, RequestModel param, final IPresenter iPresenter,boolean isToken) {
        if (NetworkUtils.netWorkJuder()) {
            Observable<ResponseModel> observable = getService(url,isToken).service(param);
            subscribe(observable, url, param, iPresenter);
        } else {
            String paramStr =BasePresenter.gson.toJson(param.getData());
            if (param.isSelect()) {//查数据
                String jsonString = AppSelectSP.getString(url + paramStr);
                if (!TextUtils.isEmpty(jsonString)) {
                    iPresenter.onSuccess(url, jsonString);
                    return;
                }
            } else {// 增删改数据
                AppIDUSP.putString(url,paramStr);
            }
            Toast.makeText(MyApplication.getInstance(), "网络不给力,请稍后再试!", Toast.LENGTH_SHORT).show();
        }
    }

    private static void subscribe(Observable<ResponseModel> observable, final String url, final RequestModel param, final IPresenter iPresenter) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onNext(ResponseModel s) {
                        String paramStr = BasePresenter.gson.toJson(param.getData());
                        try {
                            String rtnCode = s.getRtnCode();
                            String msg = s.getMsg();
                            if (REQUEST_SUCCESS.equals(rtnCode)) {
                                Object object = s.getBizData();
                                String jsonString = null;
                                if (object != null) {
                                    jsonString =BasePresenter.gson.toJson(object);
                                }
                                if(param.isSelect()) AppSelectSP.putString(url + paramStr, jsonString);
                                iPresenter.onSuccess(url, jsonString);
                            } else {
                                ToastUtil.showShort(msg);
                                iPresenter.onFail(url, rtnCode);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (!isUnsubscribed()) unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(MyApplication.getInstance(), "网络异常!", Toast.LENGTH_SHORT).show();
                        if (!isUnsubscribed()) unsubscribe();
                        iPresenter.onFail(url, "0");
                    }
                });
    }


    /**
     * 上传文件
     * @param paths
     * @param presenter
     */
    public static void uploadFiles(final List<String> paths, final UploadFilePresenter presenter) {
        //必须使用LinkedHashMap，保证文件按顺序上传
        Map<String, RequestBody> params = new LinkedHashMap<>();
        for (int i = 0; i < paths.size(); i++) {
            if(paths.get(i)==null)continue;
            File file = new File(paths.get(i));
            RequestBody filebody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), filebody);
            if (NetworkUtils.netWorkJuder()) {
                Observable<UploadFileResponseModel> observable = getService(YXBService.UPLOAD_URL,false).uploadFiles(body);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<UploadFileResponseModel>() {
                            @Override
                            public void onCompleted() {
                                if (!isUnsubscribed()) unsubscribe();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MyApplication.getInstance(), "服务器异常!", Toast.LENGTH_SHORT).show();
                                if (!isUnsubscribed()) unsubscribe();
                            }

                            @Override
                            public void onNext(UploadFileResponseModel uploadFileResponseModel) {
                                try {
                                    if(200== uploadFileResponseModel.getCode()){
                                        presenter.onSuccess(uploadFileResponseModel.getData());
                                    }else presenter.onFail();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            } else {
                Toast.makeText(MyApplication.getInstance(), "网络不给力,请稍后再试!", Toast.LENGTH_SHORT).show();
            }


        }

    }
}