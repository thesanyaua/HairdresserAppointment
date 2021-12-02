package com.example.hairdresserappointment.other;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceSetting {
   static SharedPreferences sharedPreferences=null;
   static SharedPreferences.Editor editor=null;

    public static SharedPreferences getSPSetting(Context context) {
        if(sharedPreferences==null) {
            sharedPreferences = context.getSharedPreferences("SettingPreference", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }


    public static SharedPreferences.Editor getSPEditor(Context context) {
        if (editor==null) {
            editor = getSPSetting(context).edit();
        }
        return editor;
    }
}
