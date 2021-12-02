package com.example.hairdresserappointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.hairdresserappointment.other.SettingViewModel;
import com.example.hairdresserappointment.other.SharePreferenceSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingViewModel settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        settingViewModel.setApplicationTheme(this);
        setContentView(R.layout.activity_main);
    }


}