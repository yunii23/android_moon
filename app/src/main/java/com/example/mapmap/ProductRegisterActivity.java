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

public class ProductRegisterActivity extends AppCompatActivity {
    private EditText et_product_name, et_product_money, et_product_date;
    private Button btn_product_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_register);

        et_product_name = (EditText) findViewById(R.id.et_product_name);
        et_product_money = (EditText) findViewById(R.id.et_product_money);
        et_product_date = (EditText) findViewById(R.id.et_product_date);

        btn_product_register = (Button) findViewById(R.id.btn_product_register);

        btn_product_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져오다)해온다.
                String productName = et_product_name.getText().toString();
                String productMoney = et_product_money.getText().toString();
                String productDate = et_product_date.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success"); //php 파일에 success
                            if (success) {
                                Toast.makeText(getApplicationContext(), "상품등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProductRegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패할 경우
                                Toast.makeText(getApplicationContext(), "상품등록에 실패하셨습니다.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 서버로 Volley를 이용해서 요청을 함
                ProductRegisterRequest productregisterRequest = new ProductRegisterRequest(productName, productMoney, productDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProductRegisterActivity.this);
                queue.add(productregisterRequest);
            }
        });
    }
}
