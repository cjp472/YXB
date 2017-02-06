package com.cicada.yuanxiaobao.home.view;

import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.GlideCircleTransform;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.home.action.INavigationAction;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.home.presenter.NavigationPresenter;
import com.cicada.yuanxiaobao.utils.AppSelectSP;

import butterknife.Bind;


/**
 * Created by tanglei on 16/6/17.
 */
public class NavigationFragment extends BaseFragment implements INavigationAction {

    @Bind(R.id.list_view)
    ListView mListView;
    @Bind(R.id.userIcon)
    ImageView mUserIcon;
    @Bind(R.id.user_name)
    MyFontTextView mUserName;

    public NavigationPresenter mPresenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.navigation_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {
        mPresenter = new NavigationPresenter(mContext, this);
    }

    @Override
    public void initView() {
        mListView.setOnItemClickListener(mPresenter);
        mPresenter.initData();
        ReceiveLoginModel loginModel=BasePresenter.gson.fromJson(AppSelectSP.getString("Login"),ReceiveLoginModel.class);
        mUserName.setText(loginModel.getUserInfo().getUserName());
        Glide.with(this).load(loginModel.getUserInfo().getUserIcon()).error(R.drawable.defaultavatar).placeholder(R.drawable.defaultavatar).transform(new GlideCircleTransform(mContext)).into(mUserIcon);
    }


    @Override
    public void setAdapter(QuickAdapter adapter) {
        mListView.setAdapter(adapter);

    }

    @Override
    public void change(String str) {
        ((FragmentMainActivity) mContext).switchNavigation(str);
    }


}
