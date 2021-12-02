package com.example.hairdresserappointment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdresserappointment.other.SettingViewModel;


public class SettingFragment extends Fragment {
    ImageView setting_back, setting_save;
    RadioGroup radioGroupColors;
    TextView textSettingTeam;
    LinearLayout settingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);

        SettingViewModel settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        settingViewModel.clickRadioGroup(radioGroupColors, settingBar, getActivity().getSupportFragmentManager());
        settingViewModel.settingInSettingFragment(textSettingTeam);

        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        setting_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    public void init(View view) {
        setting_back = view.findViewById(R.id.setting_back);
        setting_save = view.findViewById(R.id.setting_save);
        radioGroupColors = view.findViewById(R.id.radio_group_colors);
        settingBar = view.findViewById(R.id.setting_bar);
        textSettingTeam = view.findViewById(R.id.text_them);
    }
}