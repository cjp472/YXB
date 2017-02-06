package com.cicada.yuanxiaobao.API;


import com.cicada.yuanxiaobao.common.RequestModel;
import com.cicada.yuanxiaobao.common.ResponseModel;
import com.cicada.yuanxiaobao.common.UploadFileResponseModel;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Total API
 * AUTHOR: TL
 * DATE: 21/10/2015 18:44
 */
public interface YXBAPI {



    /**
     * 通用接口调用
     * @return
     */
    @POST("yxb")
    Observable<ResponseModel> service( @Body RequestModel model);


    /**
     * 文件上传
     * @param file
     * @return
     */
    @Multipart
    @POST("file/upload/savefile.shtml/yxb")
    Observable<UploadFileResponseModel> uploadFiles(@Part MultipartBody.Part file);




}


