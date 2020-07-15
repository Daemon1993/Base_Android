package com.das.v1r1;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.das.componentbase.ServiceFactory;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.view.BaseActivity;
import com.das.v1r1.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    ActivityHomeBinding viewDataBinding;
    //底部Tab标题
    private final String[] mTitles = {"主页", "理财", "添加", "我的"};

    private List<Fragment> fragmentList;

    @Override
    protected void initLazyAction() {

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        viewDataBinding.tabMain.postDelayed(new Runnable() {
            @Override
            public void run() {

                KLog.e("改变icon");

                updateTabView(viewDataBinding.tabMain.getTabAt(0).getCustomView(), getDrawable(R.drawable.login_pwd));

            }
        }, 5000);


        for (int i = 0; i < 4; i++) {
            viewDataBinding.tabMain.addTab(viewDataBinding.tabMain.newTab().setCustomView(getTabView(i, R.drawable.home_item_icon_bg)));
        }

        viewDataBinding.tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewDataBinding.viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initFragmentList();

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewDataBinding.viewPager.setAdapter(homePagerAdapter);


    }

    private void initFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment());
        fragmentList.add(new Fragment());
//        fragmentList.add(new Fragment());

        Fragment user_fragment = (Fragment) ARouter.getInstance().build(Router_Pools.User_Center_Fragment).navigation();
        Fragment user_fragment1 = (Fragment) ARouter.getInstance().build(Router_Pools.User_Center_Fragment).navigation();
        fragmentList.add(user_fragment);
        fragmentList.add(user_fragment1);
    }


    //自定义Tab样式
    private View getTabView(final int position, int resId) {
        final View view = LayoutInflater.from(this).inflate(R.layout.home_item_menu, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        final ImageView iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
        iv_logo.setImageResource(resId);
        tv_name.setText(mTitles[position]);
        //默认第一个tab选中，设置字体为选中色
        if (position == 0) {
            tv_name.setTextColor(Color.parseColor("#4192e3"));
        } else {
            tv_name.setTextColor(Color.parseColor("#262a3b"));
        }

        return view;
    }

    private void updateTabView(View view, Drawable drawable) {

        if (view == null) {
            return;
        }
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        final ImageView iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
        iv_logo.setImageDrawable(drawable);

    }

}