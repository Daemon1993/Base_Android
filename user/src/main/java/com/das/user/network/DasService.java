package com.das.user.network;

import com.das.god_base.network.BaseResponseResult;
import com.das.user.network.request.UpdatePwd;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.network.response.Splash2Response;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface DasService {

    String BASEURL="http://192.168.100.96:8081";

    @GET("/")
    Call<String> test();

    @FormUrlEncoded
    @POST("/api/app/v1/TokenServer/Login")
    Observable<LoginResponse> login(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("/api/app/v1/TokenServer/RefreshToken")
    Observable<LoginResponse> refreshToken(@FieldMap Map<String, String> params);


    @POST("/api/app/v1/IbmsAccount/ChangePwd")
    Observable<BaseResponseResult> updatePwd(@Body UpdatePwd updatePwd);

    @POST("/api/app/v1/IbmsAccount/GetUserProfile")
    Observable<BaseResponseResult> getUserInfo();


    @POST("/api/app/v1/FlyScreen/GetFlyScreen")
    Observable<Splash2Response> getAppSplash2();



}
