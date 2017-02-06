package com.cicada.yuanxiaobao.common;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.cicada.yuanxiaobao.utils.AppIDUSP;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.utils.Logger;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by tanglei on 16/7/7.
 */
public class CommitDataService extends Service {

    private SharedPreferences sp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        sp=getSharedPreferences("AppIDUSP", Context.MODE_PRIVATE);
        sp= AppIDUSP.sp;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Map<String, String> map = (Map<String, String>)sp.getAll();
        Logger.i("map  "+map.toString());
        if(map.size()<1) return START_STICKY;
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        DataPresenter presenter=new DataPresenter();
        while (iterator.hasNext()) {
            String url = iterator.next();
            String paramString = map.get(url);
            RequestModel model= new GsonBuilder().create().fromJson(paramString,new TypeToken<RequestModel>(){}.getType());
            ApiDataFactory.serviceAPI(url,model,presenter,true);
        }
        return START_STICKY;
    }

    class DataPresenter implements IPresenter{

        @Override
        public void onSuccess(String url, String objectString) {
            Logger.i(url);
            sp.edit().remove(url).commit();
        }

        @Override
        public void onFail(String url, String rtnCodel) {}

        @Override
        public Context getContext() {return null;}
    }
}
