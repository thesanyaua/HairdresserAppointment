package com.example.hairdresserappointment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdresserappointment.adapters.AdapterDayInfo;
import com.example.hairdresserappointment.db.ClientAddDataBase;
import com.example.hairdresserappointment.other.DataInfo;
import com.example.hairdresserappointment.other.DataInfoViewModel;
import com.example.hairdresserappointment.other.SettingViewModel;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StartFragment extends Fragment {
    CalendarView calendarView;
    RecyclerView recyclerView;
    ClientAddDataBase clientAddDataBase;
    Bundle bundleSetNoteDayFragment;
    RelativeLayout start_background;
    ImageView settingInBar;
    LinearLayout startBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);



        SettingViewModel settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        settingViewModel.settingBarColor(startBar);

        settingInBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_settingFragment);
            }
        });

        clientAddDataBase = ClientAddDataBase.getInstance(getContext());


        /*View Model отвечающяя за отображение по дате в recycler*/
        DataInfoViewModel dataInfoViewModel = new ViewModelProvider(StartFragment.this).get(DataInfoViewModel.class);
        dataInfoViewModel.addListInLiveData(this, recyclerView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {
                monthName(year, month, dayOfMonth);
                NoteDayFragment noteDayFragment = new NoteDayFragment();
                bundleSetNoteDayFragment = new Bundle();
                bundleSetNoteDayFragment.putInt("dataID", Integer.parseInt("" + dayOfMonth + (month + 1) + year));
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_noteDayFragment, bundleSetNoteDayFragment);
            }
        });
    }


    public void init(View view) {
        calendarView = view.findViewById(R.id.calendar);
        recyclerView = view.findViewById(R.id.recyclerView_date);
        start_background = view.findViewById(R.id.start_background);
        settingInBar = view.findViewById(R.id.setting_in_bar);
        startBar = view.findViewById(R.id.liner_bar);
    }



    public void monthName(int year, int month, int dayOfMonth) {
        if (month == 0) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " января " + year;
        } else if (month == 1) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " февраля " + year;
        } else if (month == 2) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " марта " + year;
        } else if (month == 3) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " апреля " + year;
        } else if (month == 4) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " мая " + year;
        } else if (month == 5) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " июня " + year;
        } else if (month == 6) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " июля " + year;
        } else if (month == 7) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " августа " + year;
        } else if (month == 8) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " сентября " + year;
        } else if (month == 9) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " октября " + year;
        } else if (month == 10) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " ноября " + year;
        } else if (month == 11) {
            NoteDayFragment.dateClickInBar = "" + dayOfMonth + " декабря " + year;
        }
    }
}