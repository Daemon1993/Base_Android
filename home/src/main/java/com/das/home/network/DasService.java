package com.das.home.network;




import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface DasService {
    String BASEURL="https://www.baidu.com";


    @GET("/")
    Call<String> getData();

    @GET
    Observable<ResponseBody> test(@Url String url);
}
