package com.example.hairdresserappointment.other;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;

import com.example.hairdresserappointment.CloseFragment;
import com.example.hairdresserappointment.MainActivity;
import com.example.hairdresserappointment.R;

public class SettingViewModel extends AndroidViewModel {

    SharedPreferences sharedPreferences = SharePreferenceSetting.getSPSetting(getApplication().getApplicationContext());
    SharedPreferences.Editor editor = SharePreferenceSetting.getSPEditor(getApplication().getApplicationContext());


    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public void clickRadioGroup(RadioGroup radioGroup, LinearLayout settingBar, FragmentManager fragmentManager) {

        radioGroup.check(sharedPreferences.getInt("Radio", R.id.radioButton_green));
        settingBarColor(settingBar);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_green) {
                    getFragmentInfo(fragmentManager);
                    editor.putInt("Radio", R.id.radioButton_green);
                    editor.apply();
                    //settingBarColor(settingBar);
                } else if (checkedId == R.id.radioButton_yellow) {
                    getFragmentInfo(fragmentManager);
                    editor.putInt("Radio", R.id.radioButton_yellow);
                    editor.apply();
                    //settingBarColor(settingBar);
                } else if (checkedId == R.id.radioButton_blue) {
                    getFragmentInfo(fragmentManager);
                    editor.putInt("Radio", R.id.radioButton_blue);
                    editor.apply();
                    //settingBarColor(settingBar);
                } else if (checkedId == R.id.radioButton_red) {
                    getFragmentInfo(fragmentManager);
                    editor.putInt("Radio", R.id.radioButton_red);
                    editor.apply();
                    //settingBarColor(settingBar);
                }
            }
        });
    }

   /*Бар фрагмента settingFragment*/
    public void settingBarColor(LinearLayout settingBar) {
        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.green_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.yellow_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.blue_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.red_them));
        }
    }

    public void settingAddAndEditFragment(LinearLayout settingBar, ImageView imageView) {
        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.green_them));
            imageView.setImageResource(R.drawable.ic_baseline_contact_page_24);
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.yellow_them));
            imageView.setImageResource(R.drawable.contact_yellow);
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.blue_them));
            imageView.setImageResource(R.drawable.contact_blue);
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {
            settingBar.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.red_them));
            imageView.setImageResource(R.drawable.contact_red);
        }
    }

    public void settingInSettingFragment(TextView textView) {
        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.green_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.yellow_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.blue_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.red_them));

        }
    }

    /*Задний фон фрагмента StartFragment*/
    public void backgroundStartFragment(RelativeLayout start_background) {
        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {
            start_background.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.background_green));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {
            start_background.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.background_yellow));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {
            start_background.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.background_blue));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {
            start_background.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.background_red));
        }
    }

    /*Для изменения цвета View в ClientAdapter*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getViewInList(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getApplication().getApplicationContext()).inflate(R.layout.clientinfo_layout, parent, false);
        LinearLayout heading = view.findViewById(R.id.heading);
        TextView name_client = view.findViewById(R.id.name_client);
        TextView number_phone_client = view.findViewById(R.id.number_phone_client);
        ImageView imageCallClient = view.findViewById(R.id.imageCallClient);
        ImageView imageEdit = view.findViewById(R.id.imageEdit);
        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {

            heading.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.green_them));
            name_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.green_them));
            number_phone_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.green_them));
            imageCallClient.setImageResource(R.drawable.ic_baseline_local_phone_24);
            imageEdit.setImageResource(R.drawable.ic_baseline_edit_24);

        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {

            heading.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.yellow_them));
            name_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.yellow_them));
            number_phone_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.yellow_them));
            imageCallClient.setImageResource(R.drawable.phone_yellow);
            imageEdit.setImageResource(R.drawable.edit_yellow);

        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {

            heading.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.blue_them));
            name_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.blue_them));
            number_phone_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.blue_them));
            imageCallClient.setImageResource(R.drawable.phone_blue);
            imageEdit.setImageResource(R.drawable.edit_blue);

        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {

            heading.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.red_them));
            name_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.red_them));
            number_phone_client.setTextColor(getApplication().getApplicationContext().getColor(R.color.red_them));
            imageCallClient.setImageResource(R.drawable.phone_red);
            imageEdit.setImageResource(R.drawable.edit_red);

        }
        return view;
    }

    public View getViewInDayInfoList(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getApplication().getApplicationContext()).inflate(R.layout.date_info_layout, parent, false);
        TextView textView = view.findViewById(R.id.dayName);

        if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_green) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.green_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_yellow) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.yellow_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_blue) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.blue_them));
        } else if (sharedPreferences.getInt("Radio", R.id.radioButton_green) == R.id.radioButton_red) {
            textView.setBackground(AppCompatResources.getDrawable(getApplication().getApplicationContext(), R.color.red_them));
        }
        return view;
    }


    public void setApplicationTheme(MainActivity mainActivity) {
        if(sharedPreferences.getInt("Radio", R.id.radioButton_green)==R.id.radioButton_green){
            mainActivity.setTheme(R.style.MyGreenTheme);
        }else if(sharedPreferences.getInt("Radio", R.id.radioButton_green)==R.id.radioButton_yellow) {
            mainActivity.setTheme(R.style.MyYellowTheme);
        }else if(sharedPreferences.getInt("Radio", R.id.radioButton_green)==R.id.radioButton_blue) {
            mainActivity.setTheme(R.style.MyBlueTheme);
        }else if(sharedPreferences.getInt("Radio", R.id.radioButton_green)==R.id.radioButton_red) {
            mainActivity.setTheme(R.style.MyRedTheme);
        }
    }

    public void getFragmentInfo(FragmentManager fragmentManager){
        CloseFragment closeFragment = new CloseFragment();
        if(fragmentManager!=null) {
            closeFragment.show(fragmentManager, "casual1");
        }

    }





}
