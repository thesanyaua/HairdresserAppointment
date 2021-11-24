package com.example.hairdresserappointment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hairdresserappointment.adapters.ClientAdapter;
import com.example.hairdresserappointment.db.Client;
import com.example.hairdresserappointment.db.ClientAddDataBase;


public class EditClientFragment extends DialogFragment {

static final int REQUEST_SELECT_PHONE_NUMBER = 1;
public static ClientAdapter clientAdapter;
String id_in_bd;
int dateID;
String name_client;
String number_client;
String time_Hour;
String time_Minute;
String job_client;

EditText e_hour, e_minute, e_name_add_notatics, e_number_add_notatics;
TextView positive_click, negative_click, error_textView, edit_job_client;
ClientAddDataBase clientAddDataBase;
ImageView e_add_contact_from_contacts;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            id_in_bd = getArguments().getString("id_in_bd");
            dateID = getArguments().getInt("dateID");
            name_client = getArguments().getString("name_client");
            number_client = getArguments().getString("number_client");
            time_Hour = getArguments().getString("time_Hour");
            time_Minute = getArguments().getString("time_Minute");
            job_client = getArguments().getString("job_client");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        clientAddDataBase = ClientAddDataBase.getInstance(getContext());
        e_hour.setText(time_Hour);
        e_minute.setText(time_Minute);
        e_name_add_notatics.setText(name_client);
        e_number_add_notatics.setText(number_client);
        edit_job_client.setText(job_client);


        e_add_contact_from_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
            }
        });



        positive_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(Integer.parseInt(e_hour.getText().toString())>23||Integer.parseInt(e_minute.getText().toString())>59 ||
                            e_hour.getText().toString()==null || e_minute.getText().toString()==null) {
                        error_textView.setText("Не верно указано время");
                    }else {
                        Client client = new Client(
                                Long.parseLong(id_in_bd),
                                dateID,
                                e_name_add_notatics.getText().toString(),
                                e_number_add_notatics.getText().toString(),
                                Integer.parseInt(e_hour.getText().toString()),
                                Integer.parseInt(e_minute.getText().toString()),
                                edit_job_client.getText().toString());
                        clientAddDataBase.getClientDAO().updateClient(client);
                        clientAdapter.adapterReplace(clientAddDataBase.getClientDAO().getListDate(dateID));
                        EditClientFragment.this.onDestroyView();
                    }
                }catch (Exception e) {
                    error_textView.setText("Не верно указано время");
                }

            }
        });

        negative_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditClientFragment.this.onDestroyView();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);

        }
    }

    public void init(View view) {
        e_hour = view.findViewById(R.id.e_hour);
        e_minute = view.findViewById(R.id.e_minute);
        e_name_add_notatics = view.findViewById(R.id.e_name_add_notatics);
        e_number_add_notatics = view.findViewById(R.id.e_number_phones_add_notatics);
        positive_click = view.findViewById(R.id.positive_click);
        e_add_contact_from_contacts = view.findViewById(R.id.e_add_contact_from_contacts);
        negative_click = view.findViewById(R.id.negative_click);
        error_textView = view.findViewById(R.id.error);
        edit_job_client = view.findViewById(R.id.e_add_job_client);
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
                String number = cursor.getString(numberIndex);
                e_number_add_notatics.setText(number);

            }
        }
    }
}