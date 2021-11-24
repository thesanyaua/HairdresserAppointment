package com.example.hairdresserappointment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdresserappointment.adapters.ClientAdapter;
import com.example.hairdresserappointment.db.Client;
import com.example.hairdresserappointment.db.ClientAddDataBase;

import java.util.ArrayList;
import java.util.List;


public class NoteDayFragment extends Fragment {
    static final int REQUEST_SELECT_PHONE_NUMBER = 1;
    public static String dateClickInBar;
    public static int dataID;

    TextView textView;
    ImageView back, add;
    RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            dataID = getArguments().getInt("dataID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        ClientAddDataBase clientAddDataBase = ClientAddDataBase.getInstance(getContext());
        textView.setText(dateClickInBar);
        ClientAdapter clientAdapter = new ClientAdapter(getContext());
        clientAdapter.addListClient(clientAddDataBase.getClientDAO().getListDate(dataID));
        EditClientFragment.clientAdapter = clientAdapter;
        AddClientFragment .clientAdapter = clientAdapter;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(clientAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                AddClientFragment addClientFragment = new AddClientFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("dataID", dataID);
                addClientFragment.setArguments(bundle);
                addClientFragment.show(fragmentManager, "casual");
            }
        });
    }

    public void init(View view) {
        textView = view.findViewById(R.id.date_info);
        back = view.findViewById(R.id.back);
        add = view.findViewById(R.id.imageView_addUser);
        recyclerView = view.findViewById(R.id.recycler_list);
    }
}