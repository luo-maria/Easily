package com.example.easily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;

import cn.smssdk.SMSSDK;

class  RegisterActivity extends Activity {
    EditText username,password,SMS;
    Button register,SMSbt,Retbt;
    private String phone_number;
    private String cord_number;
    EventHandler eventHandler;
    private int time=60;
    private boolean flag=true;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findViews();
        MobSDK.init(this);
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg=new Message();
                msg.arg1=event;
                msg.arg2=result;
                msg.obj=data;
                handler.sendMessage(msg); }};
        SMSSDK.registerEventHandler(eventHandler);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
            }
        });
        Retbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,EnterActivity.class);
                startActivity(intent);
            }
        });
        SMSbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_number=username.getText().toString().trim();
                String num="[1][358]\\d{9}";
                SMSSDK.getVerificationCode("86",phone_number);
                Toast.makeText(getApplicationContext(),"验证码获取成功", Toast.LENGTH_LONG).show();
                username.requestFocus();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    /**
     * 使用Handler来分发Message对象到主线程中，处理事件
     */
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event=msg.arg1;
            int result=msg.arg2;
            Object data=msg.obj;
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                if(result == SMSSDK.RESULT_COMPLETE) {
                    boolean smart = (Boolean)data;
                    if(smart) {
                        Toast.makeText(getApplicationContext(),"该手机号已经注册过，请重新输入",
                                Toast.LENGTH_LONG).show();
                        username.requestFocus();
                        return;
                    } } }
            if(result==SMSSDK.RESULT_COMPLETE)
            { if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                Toast.makeText(getApplicationContext(), "注册成功",
                        Toast.LENGTH_LONG).show();
            }
            }
            else
            {
                if(flag)
                {
                    SMSbt.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
                    username.requestFocus();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
                }
            }
        }

    };
    private void findViews() {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        register=(Button) findViewById(R.id.reg);
        SMS = findViewById(R.id.code);
        SMSbt = findViewById(R.id.code_but);
//        Retbt = findViewById(R.id.bn_return);
    }
    private boolean judCord() {
        judPhone();
        if(TextUtils.isEmpty(SMS.getText().toString().trim()))
        {
            Toast.makeText(RegisterActivity.this,"请输入您的验证码",Toast.LENGTH_LONG).show();
            SMS.requestFocus();
            return false;
        }
        else if(SMS.getText().toString().trim().length()!=6)
        {
            Toast.makeText(RegisterActivity.this,"您的验证码位数不正确",Toast.LENGTH_LONG).show();
            SMS.requestFocus();
            return false;
        }
        else
        {
            cord_number=SMS.getText().toString().trim();
            return true;
        }
    }
    private boolean judPhone() {
        if(TextUtils.isEmpty(username.getText().toString().trim()))
        {
            Toast.makeText(RegisterActivity.this,"请输入您的电话号码",Toast.LENGTH_LONG).show();
            username.requestFocus();
            return false;
        }
        else if(username.getText().toString().trim().length()!=11)
        {
            Toast.makeText(RegisterActivity.this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            username.requestFocus();
            return false;
        }
        else
        {
            phone_number=username.getText().toString().trim();
            String num="[1][358]\\d{9}";
            if(phone_number.matches(num))
                return true;
            else
            {
                Toast.makeText(RegisterActivity.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
    /***
     * 跳转到登录界面
     **/
    private void returnEnter() {
        Intent intent = new Intent(this,EnterActivity.class);
        startActivity(intent);
    }
}