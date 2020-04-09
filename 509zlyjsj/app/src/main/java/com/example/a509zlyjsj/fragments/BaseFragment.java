package com.example.a509zlyjsj.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    private final String KEY_SESSION_ID = "sessionID";//与sharedpreferences保存的关键字一致
    private final String KEY_USERNAME = "username";//与sharedpreferences保存的关键字一致

    private final String FILE = "login";//与sharedpreferences的文件名一致
    private final int MODE = Context.MODE_PRIVATE;

    private SharedPreferences sharedPreferences;
    protected Context context;

    @Override  //Fragment生命周期方法
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE, MODE);
    }
    //返回sessionid
    protected String getSessionId(){
        Log.i("sessionID", "getSessionId: "+sharedPreferences.getString(KEY_SESSION_ID, ""));
        return sharedPreferences.getString(KEY_SESSION_ID, "");
    }
    //返回username
    protected String getUserName(){
        return sharedPreferences.getString(KEY_USERNAME, "");
    }
}
