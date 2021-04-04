package com.example.myapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        //회원가입버튼클릭
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( packageContent LoginActivity.this.RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean(name:"success");
                            if(success){//로그인성공
                                String userID = JSONObject.getString(name:"userID");
                                String userPass = JSONObject.getString(name:"userPassword");
                                Toast.makeText(getApplicationContext(),text:"로그인에 성공하였습니다.",Toast.LENGTH_SHORT.show();
                                Intent intent = new Intent(packageContext LoginActivity.this, MainActivity.class);
                                intent.putExtra(name:"userID",userID);
                                intent.putExtra(name:"userPass",userPass);
                                startActivity(intent);
                            }else{//로그인실패
                                Toast.makeText(getApplicationContext(),text:"로그인에 실패하였습니다.",Toast.LENGTH_SHORT.show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LogintRequest(userID, userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(content: LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}