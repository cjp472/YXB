package com.cicada.yuanxiaobao.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/20.
 */
public class UploadFilePresenter {

    private IUploadFile mIUploadFile;
    private int size,i;
    private List<String> mList=new ArrayList<>();

    public UploadFilePresenter(IUploadFile IUploadFile) {
        mIUploadFile = IUploadFile;
    }

    public void onSuccess(FileInfo fileInfo) {
        mList.add(fileInfo.getUrl());
        i++;
        if(i==size){
            mIUploadFile.uploadFileCompleted(mList);
        }
    }

    public void onFail() {

    }


    public void upload(List<String> list) {
        i=0;
        mList.clear();
        this.size = list.size();
        ApiDataFactory.uploadFiles(list, this);
    }


}
