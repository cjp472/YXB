package com.cicada.yuanxiaobao.home.presenter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.MyApplication;
import com.cicada.yuanxiaobao.home.action.INavigationAction;
import com.cicada.yuanxiaobao.home.adapter.NavigatiionListAdapter;
import com.cicada.yuanxiaobao.home.model.MenuModel;
import com.cicada.yuanxiaobao.home.model.NavigationListItemModel;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanglei on 16/7/5.
 */
public class NavigationPresenter implements AdapterView.OnItemClickListener {

    private Context mContext;
    private INavigationAction mINavigationAction;

    public NavigationPresenter(Context context, INavigationAction INavigationAction) {
        mContext = context;
        mINavigationAction = INavigationAction;
    }

    private NavigatiionListAdapter mListAdapter;
    private int dra[] = {R.drawable.home1, R.drawable.examine1, R.drawable.attendance1, R.drawable.archive1, R.drawable.assessment1};
    private List<NavigationListItemModel> data;
    private int position = 0;

    public void initData() {
        String str = AppSelectSP.getString("Login");
        ReceiveLoginModel loginModel = BasePresenter.gson.fromJson(str, ReceiveLoginModel.class);
        List<MenuModel> listMenu = loginModel.getRights();
        Map<String, NavigationListItemModel> map = getNavigationModelMap();
        data = new ArrayList<NavigationListItemModel>();
        for (int i = 0; i < listMenu.size(); i++) {
            if (i == 0) map.get(listMenu.get(i).getMenuName()).setSelect(true);
            data.add(map.get(listMenu.get(i).getMenuName()));
        }
        mListAdapter = new NavigatiionListAdapter(mContext, R.layout.navigation_list_item, data);
        mINavigationAction.setAdapter(mListAdapter);
        mINavigationAction.change(listMenu.get(0).getMenuName());
    }


    private Map<String, NavigationListItemModel> getNavigationModelMap() {
        Map<String, NavigationListItemModel> map = new HashMap<>();
        String[] strTitle = MyApplication.getInstance().getResources().getStringArray(R.array.navigation_title_permission1);
        for (int i = 0; i < strTitle.length; i++) {
            NavigationListItemModel model = new NavigationListItemModel();
            model.setTitle(strTitle[i]);
            model.setDrawableId1(dra[i]);
            map.put(strTitle[i], model);
        }
        return map;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        view.findViewById(R.id.image).getWidth();
        if (this.position != position) {
            AppCompatTextView title = (AppCompatTextView) view.findViewById(R.id.title);
            mINavigationAction.change(title.getText().toString());
            data.get(position).setSelect(true);
            data.get(this.position).setSelect(false);
            this.position = position;
            mListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取当前选中的导航标题
     *
     * @return
     */
    public String getCurrentItemTitle() {
        return data.get(position).getTitle();
    }

}
