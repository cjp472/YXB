package com.cicada.yuanxiaobao.attendance.view;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IFamilyMemberAction;
import com.cicada.yuanxiaobao.attendance.adapter.ParentsPhoneAdapter;
import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.attendance.presenter.KidArchiveDetailPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.GlideCircleTransform;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/8.
 */
public class KidArchiveDetailFragment extends BaseFragment implements View.OnClickListener, IFamilyMemberAction {
    @Bind(R.id.tavatar)
    ImageView mTavatar;
    @Bind(R.id.name)
    MyFontTextView mName;
    @Bind(R.id.age)
    MyFontTextView mAge;
    @Bind(R.id.group)
    MyFontTextView mGroup;
    @Bind(R.id.relative)
    RelativeLayout mRelative;
    @Bind(R.id.archive)
    MyFontTextView mArchive;
    @Bind(R.id.status)
    MyFontTextView mStatus;
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.morn_noon_result)
    MyFontTextView mMornNoonResult;
    @Bind(R.id.entrust)
    MyFontTextView mEntrust;
    @Bind(R.id.special_situation)
    MyFontTextView mSpecialSituation;
    @Bind(R.id.vaccine)
    MyFontTextView mVaccine;
    @Bind(R.id.physique)
    MyFontTextView mPhysique;
    @Bind(R.id.corporeity)
    MyFontTextView mCorporeity;
    @Bind(R.id.smart)
    MyFontTextView mSmart;
    @Bind(R.id.tab_content)
    FrameLayout mTabContent;
    @Bind(R.id.down)
    ImageView mDown;
    @Bind(R.id.top_layout)
    RelativeLayout mTopLayout;

    private PopupWindow popupWindow = new PopupWindow();
    private TextView tmpTextView;
    private KidModel mKidModel;
    private boolean isTopWhite;
    private FragmentManager mFragmentManager;
    private MornNoonExaminRecordFragment mExaminResultFragment = new MornNoonExaminRecordFragment();
    private EntrustFragment mEntrustFragment = new EntrustFragment();
    private CorporeityFragment mCorporeityFragment = new CorporeityFragment();
    private PhysiqueFragment mPhysiqueFragment = new PhysiqueFragment();
    private SmartFragment mSmartFragment = new SmartFragment();
    private SpecialSituationFragment mSpecialSituationFragment = new SpecialSituationFragment();
    private VaccineFragment mVaccineFragment = new VaccineFragment();
    private Fragment[] mFragments = {mExaminResultFragment, mEntrustFragment, mSpecialSituationFragment, mVaccineFragment, mPhysiqueFragment, mCorporeityFragment, mSmartFragment};
    private KidArchiveDetailPresenter mPresenter;
    private long inspectTime;
    private List<ParentsModel> data;
    private boolean isFrist;

    @Override
    protected int getLayoutResId() {
        return R.layout.special_focus_right_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mPresenter = new KidArchiveDetailPresenter(this, mContext);
    }

    @Override
    public void initView() {
        AppTools.expandViewTouchDelegate(mDown, 50, 50, 50, 50);
        try {
            if (isTopWhite)
                mTopLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.sick_top1_bg));
            mFragmentManager = getFragmentManager();
            mName.setText(mKidModel.getChildName());
            mAge.setText(mKidModel.getChildAge() + "岁");
            Glide.with(mContext).load(mKidModel.getChildIcon()).transform(new GlideCircleTransform(mContext)).into(mTavatar);
            Drawable drawable = null;
            if ("女".equals(mKidModel.getChildSex())) {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.women);
            } else {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.man);
            }
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            mName.setCompoundDrawables(null, null, drawable, null);//画在右边
            mPresenter.queryFamilyMember(mKidModel.getChildId());
            mExaminResultFragment.setInspectTime(inspectTime);
            mExaminResultFragment.setChildId(mKidModel.getChildId());
            mExaminResultFragment.setFragment(this);
            mSpecialSituationFragment.setChildId(mKidModel.getChildId());
            mMornNoonResult.performClick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.down, R.id.morn_noon_result, R.id.entrust, R.id.special_situation, R.id.vaccine, R.id.physique, R.id.corporeity, R.id.smart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.morn_noon_result:
                changeTab(0, (TextView) view);
                break;
            case R.id.entrust:
                changeTab(1, (TextView) view);
                break;
            case R.id.special_situation:
                changeTab(2, (TextView) view);
                break;
            case R.id.vaccine:
                changeTab(3, (TextView) view);
                break;
            case R.id.physique:
                changeTab(4, (TextView) view);
                break;
            case R.id.corporeity:
                changeTab(5, (TextView) view);
                break;
            case R.id.smart:
                changeTab(6, (TextView) view);
                break;
            case R.id.up:
                popupWindow.dismiss();
                break;
            case R.id.down:
                show();
                break;
        }
    }

    int index;
    private void changeTab(int i, TextView textView) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (i != index) transaction.hide(mFragments[index]);
        boolean contains = mFragmentManager.getFragments().contains(mFragments[i]);
        if (contains) {
            transaction.show(mFragments[i]);
        } else {
            transaction.add(R.id.tab_content, mFragments[i]);
        }
        transaction.commit();
        textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.foucs_tab_bg));
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlue));
        if (tmpTextView != null && i != index) {
            tmpTextView.setBackground(null);
            tmpTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
        }
        index = i;
        tmpTextView = textView;
    }

    public void show() {
        try {
            int w = mListview.getWidth();
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.parents_popup_window, null);
            ParentsPhoneAdapter adapter = new ParentsPhoneAdapter(mContext, R.layout.parents_phone_item, data);
            ListView listView = (ListView) contentView.findViewById(R.id.list_view);
            View up = contentView.findViewById(R.id.up);
            AppTools.expandViewTouchDelegate(up, 50, 50, 50, 50);
            up.setOnClickListener(this);
            listView.setAdapter(adapter);
            popupWindow.setContentView(contentView);
            popupWindow.setWidth(w);
            if (data.size() > 5) {
                popupWindow.setHeight(DeviceUtils.dip2px(mContext, 200));
            } else popupWindow.setHeight(DeviceUtils.dip2px(mContext, 40 * data.size()));
            popupWindow.setAnimationStyle(R.style.popwin_anim_style);
            ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(mContext, android.R.color.transparent));
            popupWindow.setBackgroundDrawable(dw);
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(mTopLayout, 1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param kidModel
     * @param topWhite toplayout 背景色 是否为白色
     */
    public void setKidModel(KidModel kidModel, boolean topWhite, long inspectTime) {
        mKidModel = kidModel;
        isTopWhite = topWhite;
        this.inspectTime = inspectTime;
        initView();
        if (isFrist) {
            refresh();
        } else isFrist = true;
    }

    public void refresh() {
        mExaminResultFragment.onrefresh();
        mSpecialSituationFragment.onrefresh();
    }


    @Override
    public void setAdapter(QuickAdapter adapter,List<ParentsModel> data) {
        mListview.setAdapter(adapter);
        this.data = data;
    }

    public void setClassName(String className) {
        mGroup.setText(className);
    }


}
