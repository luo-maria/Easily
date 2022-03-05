package com.example.easily;//package com.example.easytest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DatebaseHelper extends SQLiteOpenHelper {
    private static  final String DBNAME="Association.db";
    private static final int VERSION=1;
    public DatebaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    public DatebaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //1 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        db.execSQL("create table tb_userinfo(id integer primary key autoincrement,name varchar(10),pwd varchar(15),email varchar(50),phone varchar(11))");

        //创建捐款表
        db.execSQL("create table tb_juankuan(id integer primary key autoincrement,money double,time varchar(20),payer varchar(30),payee varchar(100),remark varchar(500))");

        //创建求助表
        db.execSQL("create table tb_help (id integer primary key autoincrement,name varchar(50),address varchar(100),phone varchar(11),des varchar(200))");


    }

    //2 升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
