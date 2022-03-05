package com.example.easily;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserService {

    private DatabBaseHelper dbHelper;
    private static final String name = "mydatabase.db"; //数据库名称
    private static final int version = 1; //数据库版本
    private ArrayList<String> usernameList = new ArrayList<>();

    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    //注册用
    public boolean register(User user) {
        //用getReadable和getWriteable都可以创建或者打开一个数据库并返回一个可对数据库进行读写操作的对象，当数据库满R可以只读，W会报错
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into user(username,password) values(?,?)";
        Object obj[] = {user.getUsername(), user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public ArrayList<String> getAll() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        //查询获得游标
        Cursor cursor = sdb.query("user", null, null, null, null, null, null);
        //判断游标是否为空
        if (cursor.moveToFirst()) {
            //遍历游标
            do {
                //获得用户名
                usernameList.add(cursor.getString(1));
            } while (cursor.moveToNext());
            cursor.close();


        }
        return usernameList;
    }

    //登录用
    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
}