package com.cicada.yuanxiaobao.home.view;

import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.archive.view.KidArchiveFragment;
import com.cicada.yuanxiaobao.assessment.view.HabitusAssessmentFragment;
import com.cicada.yuanxiaobao.attendance.view.AttendanceFragment;
import com.cicada.yuanxiaobao.common.BaseActivity;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.ICurrentStack;
import com.cicada.yuanxiaobao.examine.view.MornAndNoonExamineFragment;
import com.cicada.yuanxiaobao.utils.ToastUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tanglei on 16/6/17.
 */
public class FragmentMainActivity extends BaseActivity {

    public FragmentManager mFragmentManager;
    private Map<String, Fragment> mapFragments = new HashMap<>();
    private Fragment[] mFragments = {new HomeFragment(), new MornAndNoonExamineFragment(), new AttendanceFragment(), new KidArchiveFragment(), new HabitusAssessmentFragment()};
    private Fragment mFragment;
    private NavigationFragment mNavigationFragment;
    private long mExitTime;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_activity;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {
    }

    @Override
    public void initView() {
        mFragmentManager = getSupportFragmentManager();
        mNavigationFragment = (NavigationFragment) mFragmentManager.findFragmentById(R.id.navigation_fragment);
        String[] strTitle = getResources().getStringArray(R.array.navigation_title_permission1);
        for (int i = 0; i < strTitle.length; i++) {
            mapFragments.put(strTitle[i], mFragments[i]);
        }
    }

    /**
     * 通过标题 切换Fragment
     * @param navTitle 选中的标题
     */
    public void switchNavigation(String navTitle) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = ((ICurrentStack) mapFragments.get(navTitle)).get().peek();
        List<Fragment> listFragment = mFragmentManager.getFragments();
        if (!listFragment.contains(fragment)) {
            transaction.add(R.id.content, fragment);
        }
        if (mFragment != null) transaction.hide(mFragment);
        transaction.show(fragment);
        transaction.commit();
        mFragment = fragment;
    }

    /**
     * 开启一个Fragment
     * @param fragment 新fragment
     * @param str 当前选中导航栏标题
     */
    public void startFragment(Fragment fragment, String str) {
        Stack<Fragment> stack = ((ICurrentStack) mapFragments.get(str)).get();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.content, fragment);
        transaction.hide(stack.peek());
        transaction.commit();
        stack.push(fragment);
        mFragment = fragment;
    }

    /**
     * Fragmeng回退
     * @param str 当前选中导航栏标题
     */
    public void backFragment(String str) {
        Stack<Fragment> stack = ((ICurrentStack) mapFragments.get(str)).get();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = stack.pop();
        removeChildFragment(fragment, transaction);
        transaction.show(stack.peek());
        transaction.commit();
    }

    private void removeChildFragment(Fragment fragment, FragmentTransaction transaction) {
        transaction.remove(fragment);
        List<Fragment> listFragment = fragment.getChildFragmentManager().getFragments();
        if (listFragment != null) {
            for (int i = 0; i < listFragment.size(); i++) {
                Fragment f = listFragment.get(i);
                removeChildFragment(f, transaction);
            }
        }
    }


    @Override
    public void onBackPressed() {
        String str = mNavigationFragment.mPresenter.getCurrentItemTitle();
        Stack<Fragment> stack = ((ICurrentStack) mapFragments.get(str)).get();
        if (stack.size() > 1) {
            ((BaseFragment) stack.peek()).back();
            return;
        }

        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.showShort("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
            return;
        } else {
            Process.killProcess(Process.myPid());
            System.exit(0);
            finish();
        }
        super.onBackPressed();
    }
}
