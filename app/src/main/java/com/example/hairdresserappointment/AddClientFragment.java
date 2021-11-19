package com.example.hairdresserappointment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import com.example.hairdresserappointment.adapters.ClientAdapter;
import com.example.hairdresserappointment.db.Client;
import com.example.hairdresserappointment.db.ClientAddDataBase;

public class AddClientFragment extends DialogFragment {

    static final int REQUEST_SELECT_PHONE_NUMBER = 1;
    public static ClientAdapter clientAdapter;
    int dateID;

    EditText add_hour, add_minute, add_name_client, add_number_client;
    TextView add_positive_click, add_negative_click, add_error, add_job_client;
    ClientAddDataBase clientAddDataBase;
    ImageView add_get_contact;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
           dateID = getArguments().getInt("dataID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        clientAddDataBase = Room.databaseBuilder(getContext(), ClientAddDataBase.class, "ClientDatabase").allowMainThreadQueries().build();
        add_get_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
            }
        });

        add_positive_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(add_hour.getText().toString()) > 23 || Integer.parseInt(add_minute.getText().toString()) > 59 ||
                            add_hour.getText().toString() == null || add_minute.getText().toString() == null) {
                        add_error.setText("Не верно указано время");
                    } else {
                        Client client = new Client(
                                0,
                                dateID,
                                add_name_client.getText().toString(),
                                add_number_client.getText().toString(),
                                Integer.parseInt(add_hour.getText().toString()),
                                Integer.parseInt(add_minute.getText().toString()),
                                add_job_client.getText().toString());
                        clientAddDataBase.getClientDAO().addClient(client);
                        clientAdapter.adapterReplace(clientAddDataBase.getClientDAO().getListDate(dateID));
                        AddClientFragment.this.onDestroyView();
                    }
                }catch (Exception e) {
                    add_error.setText("Не верно указано время");
                }
            }
        });


        add_negative_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClientFragment.this.onDestroyView();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void init(View view) {
        add_hour = view.findViewById(R.id.add_hour);
        add_minute = view.findViewById(R.id.add_minute);
        add_name_client = view.findViewById(R.id.add_name_client);
        add_number_client = view.findViewById(R.id.add_number_client);
        add_positive_click = view.findViewById(R.id.add_positive_click);
        add_negative_click = view.findViewById(R.id.add_negative_click);
        add_error = view.findViewById(R.id.add_error);
        add_get_contact = view.findViewById(R.id.add_get_contact);
        add_job_client = view.findViewById(R.id.add_job_client);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContext().getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String number = cursor.getString(numberIndex);
                add_number_client.setText(number);
            }
        }
    }
}
