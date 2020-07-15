package com.das.user.user_center;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.view.BaseFragment;
import com.das.user.R;
import com.das.user.RouteUtils;
import com.das.user.databinding.UserFragmentUserBinding;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.response.UserInfoResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@Route(path = Router_Pools.User_Center_Fragment)
public class UserFragment extends BaseFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    UserFragmentUserBinding inflate;

    @Override
    protected View onNewCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        inflate = DataBindingUtil.inflate(inflater, R.layout.user_fragment_user, container, false);
        initView();
        return inflate.getRoot();
    }

    private void initView() {

        TextView tv_title = inflate.clTop1.findViewById(R.id.tv_title);
        tv_title.setText("设置");

        BaseViewDataUtils.layout_base_textview_style1DataAction(inflate.clYqUser, "邀请好友", null);
        BaseViewDataUtils.layout_base_textview_style1DataAction(inflate.clSet, "设置", null);

        inflate.clYqUser.setOnClickListener(this);
        inflate.clSet.setOnClickListener(this);


        getuserInfo();

    }

    private void getuserInfo() {

        UserRetrofitHandler.getInstance().createDasService().getUserInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(new DObserver<UserInfoResponse>() {
                    @Override
                    public void onSuccess(UserInfoResponse response) {
                        showUserInfo(response);
                    }
                });

    }

    private void showUserInfo(UserInfoResponse response) {
        UserInfoResponse.DataBean data = response.getData();

        inflate.tvName.setText(data.getFullName()+"");
        inflate.tvId.setText(data.getUserName()+"");
        inflate.tvPhone.setText(data.getMobile()+"");

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cl_yq_user) {
            RouteUtils.openActivity(getContext(), InviteUserActivity.class);
            return;
        }
        if (v.getId() == R.id.cl_set) {
            RouteUtils.openActivity(getContext(), SetActivity.class);
            return;
        }


    }
}