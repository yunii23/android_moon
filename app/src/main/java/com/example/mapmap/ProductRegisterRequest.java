package com.example.mapmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProductRegisterRequest extends StringRequest {

    // 서버 URL 실행
    final static private String URL = "http://yunii23.dothome.co.kr/ProductRegister.php";
    private Map<String, String> map;

    public ProductRegisterRequest(String productName, String productMoney, String productDate, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("productName", productName);
        map.put("productMoney", productMoney);
        map.put("productDate", productDate);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
