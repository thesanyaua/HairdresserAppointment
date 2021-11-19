package com.example.hairdresserappointment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdresserappointment.db.ClientAddDataBase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StartFragment extends Fragment {
    CalendarView calendarView;
    TextView number_notes, nextDay, otherDay, date_nextDay_textView, date_otherDay_textView;
    ClientAddDataBase clientAddDataBase;
    CardView infoDateNow, cardViewNextDay, cardViewOtherDay;
    Bundle bundleSetNoteDayFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        clientAddDataBase = Room.databaseBuilder(getContext(), ClientAddDataBase.class, "ClientDatabase").allowMainThreadQueries().build();
        nowDate(view);
        nextDay(view);
        otherDay(view);


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
        number_notes = view.findViewById(R.id.number_notes);
        nextDay = view.findViewById(R.id.next_day_textView);
        otherDay = view.findViewById(R.id.other_day_textView);
        date_nextDay_textView = view.findViewById(R.id.date_nextDay_textView);
        date_otherDay_textView = view.findViewById(R.id.date_otherDay_textView);
        infoDateNow = view.findViewById(R.id.info_date_now);
        cardViewNextDay = view.findViewById(R.id.card_view_next_day);
        cardViewOtherDay = view.findViewById(R.id.card_view_other_day);
    }
    public void nowDate(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatID = createSimpleDateFormat("ddMMyyyy");
        SimpleDateFormat day = createSimpleDateFormat("dd");
        SimpleDateFormat month = createSimpleDateFormat("MM");
        SimpleDateFormat year = createSimpleDateFormat("yyyy");
        int date = Integer.parseInt(simpleDateFormatID.format(calendar.getTime()));
        number_notes.setText(""+clientAddDataBase.getClientDAO().getListDate(date).size());
        infoDateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("dataID", date);
                Navigation.findNavController(view).navigate(R.id.noteDayFragment, bundle);
                monthName(Integer.parseInt(year.format(calendar.getTime())), (Integer.parseInt(month.format(calendar.getTime()))-1),Integer.parseInt(day.format(calendar.getTime())));
            }
        });
    }

    public void nextDay(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat simpleDateFormatID = createSimpleDateFormat("ddMMyyyy");
        SimpleDateFormat simpleDateFormat = createSimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat day = createSimpleDateFormat("dd");
        SimpleDateFormat month = createSimpleDateFormat("MM");
        SimpleDateFormat year = createSimpleDateFormat("yyyy");
        int nextDayValue = Integer.parseInt(simpleDateFormatID.format(calendar.getTime()));
        date_nextDay_textView.setText(""+simpleDateFormat.format(calendar.getTime()));
        nextDay.setText(""+clientAddDataBase.getClientDAO().getListDate(nextDayValue).size());
        cardViewNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("dataID", nextDayValue);
                Navigation.findNavController(view).navigate(R.id.noteDayFragment, bundle);
                monthName(Integer.parseInt(year.format(calendar.getTime())), (Integer.parseInt(month.format(calendar.getTime()))-1),Integer.parseInt(day.format(calendar.getTime())));
            }
        });
    }

    public void otherDay(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        SimpleDateFormat simpleDateFormatID = createSimpleDateFormat("ddMMyyyy");
        SimpleDateFormat simpleDateFormat = createSimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat day = createSimpleDateFormat("dd");
        SimpleDateFormat month = createSimpleDateFormat("MM");
        SimpleDateFormat year = createSimpleDateFormat("yyyy");
        int otherDayValue = Integer.parseInt(simpleDateFormatID.format(calendar.getTime()));
        date_otherDay_textView.setText(""+simpleDateFormat.format(calendar.getTime()));
        otherDay.setText(""+clientAddDataBase.getClientDAO().getListDate(otherDayValue).size());
        cardViewOtherDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("dataID", otherDayValue);
                Navigation.findNavController(view).navigate(R.id.noteDayFragment, bundle);
                monthName(Integer.parseInt(year.format(calendar.getTime())), (Integer.parseInt(month.format(calendar.getTime()))-1),Integer.parseInt(day.format(calendar.getTime())));
            }
        });
    }
    public SimpleDateFormat createSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault());
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