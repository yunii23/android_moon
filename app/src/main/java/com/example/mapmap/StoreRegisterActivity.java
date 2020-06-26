package com.example.mapmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreRegisterActivity extends AppCompatActivity {
    private EditText et_store_name, et_store_pname, et_store_address;
    private Button btn_store_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_register);

        // 아이디값 찾아주기
        et_store_name = (EditText) findViewById(R.id.et_store_name);
        et_store_pname = (EditText) findViewById(R.id.et_store_pname);
        et_store_address = (EditText) findViewById(R.id.et_store_address);

        // 등록완료 버튼 클릭 시 수행
        btn_store_register = (Button) findViewById(R.id.btn_store_register);

        btn_store_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져오다)해온다.
                String storeName = et_store_name.getText().toString();
                String storePName = et_store_pname.getText().toString();
                String storeAddress = et_store_address.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success"); //php 파일에 success
                            if (success) {
                                Toast.makeText(getApplicationContext(), "가맹점 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(StoreRegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 가맹점등록에 실패할 경우
                                Toast.makeText(getApplicationContext(), "가맹점 등록에 실패하셨습니다.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 서버로 Volley를 이용해서 요청을 함
                StoreRegisterRequest storeregisterRequest = new StoreRegisterRequest(storeName, storePName, storeAddress, responseListener);
                RequestQueue queue = Volley.newRequestQueue(StoreRegisterActivity.this);
                queue.add(storeregisterRequest);
            }
        });
    }
}
