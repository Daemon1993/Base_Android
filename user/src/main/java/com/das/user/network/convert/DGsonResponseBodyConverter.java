package com.das.user.network.convert;

import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.ServerException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.socks.library.KLog;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class DGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {


    private static final int SUCCESS = 0;       // 成功
    private static final int TOKEN_EXPIRE = 2;  // token过期
    private static final int SERVER_EXCEPTION = 3;  // 服务器异常

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
//        KLog.d("---GsonBaseResponseBodyConverter \n"+json);

        try {
            verify(json);
            return adapter.read(gson.newJsonReader(new StringReader(json)));
        } finally {
            value.close();
        }
    }


    private void verify(String json) {
        BaseResponseResult result = gson.fromJson(json, BaseResponseResult.class);

        if(result.retCode != SUCCESS) {

            throw new ServerException(result.retCode,result.message+"");
//            switch (result.state) {
//                case FAILURE:
//                case SERVER_EXCEPTION:
//                    throw new ApiException(result.msg);
//                case TOKEN_EXPIRE:
//                    throw new TokenExpireException(result.msg);
//                default:
//                    throw new UndefinedStateException();
//            }
        }
    }
}
