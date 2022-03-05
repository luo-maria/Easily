package com.example.easily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editPerson,editCode;//用户名，密码输入框
    private Button btnEnter;//登录按钮
    private Button btnReg;//注册按钮
    private Button btnFor;//忘记密码按钮
    private String currentUesrName,currentPassword;
    private ImageView qq,wechat,weibo;
    CheckBox remember,cb;
    private ArrayList<String> usernamelList;
    private UserService uService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initViews();
        SharedPreferences sp = getSharedPreferences("0601sp", Context.MODE_PRIVATE);
        String currentUesrName = sp.getString("username", "");
        editPerson.setText(currentUesrName);
        String currentPassword = sp.getString("password", "");
        editCode.setText(currentPassword);
    }
    private void initViews() {

        btnEnter=(Button) findViewById(R.id.but_enter);
        btnReg=(Button) findViewById(R.id.but_reg);
        btnFor=(Button) findViewById(R.id.but_for);
        editPerson=(EditText) findViewById(R.id.et_username);
        editCode=(EditText) findViewById(R.id.et_password);
        btnEnter.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        uService = new UserService(EnterActivity.this);
        usernamelList = uService.getAll(); }
    protected void onDestroy() {//销毁
        super.onDestroy(); }
    protected void onResume() {
        super.onResume();
        usernamelList.clear();      //从注册返回时清除usernamelList
        usernamelList = uService.getAll(); //更新注册的内容
    }
    //按钮点击事件
    @Override
    public void onClick(View v) {
/*
        String phone_number=edit_phone.getText().toString();//1
        String cord_number=bt_getcord.getText().toString().trim();//1
*/
        switch (v.getId()){
            case R.id.but_enter://登录监听
                String name=editPerson.getText().toString();
                String pass=editCode.getText().toString();
                boolean flag=uService.login(name, pass);
                if(flag){
                    Log.i("TAG","登录成功");
                    Intent intent=new Intent(EnterActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(EnterActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.i("TAG","用户名或密码错误");
                    Toast.makeText(EnterActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.but_reg://注册监听
                Intent intent=new Intent(EnterActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}



