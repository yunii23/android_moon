package com.example.mapmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StoreRegisterRequest extends StringRequest {
    // 서버 URL 실행
    final static private String URL = "http://yunii23.dothome.co.kr/StoreRegister.php";
    private Map<String, String> map;

    public StoreRegisterRequest(String storeName, String storePName, String storeAddress, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("storeName", storeName);
        map.put("storePName", storePName);
        map.put("storeAddress", storeAddress);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
