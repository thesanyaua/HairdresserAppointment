package com.example.hairdresserappointment.adapters;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.room.Room;

import com.example.hairdresserappointment.DataBaseViewModel;
import com.example.hairdresserappointment.EditClientFragment;
import com.example.hairdresserappointment.MainActivity;
import com.example.hairdresserappointment.NoteDayFragment;
import com.example.hairdresserappointment.R;
import com.example.hairdresserappointment.db.Client;
import com.example.hairdresserappointment.db.ClientAddDataBase;
import com.example.hairdresserappointment.other.SettingViewModel;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClienrAdapterViewHolder> {
    SortedList<Client> clients = new SortedList<>(Client.class, new SortedList.Callback<Client>() {
        @Override
        public int compare(Client o1, Client o2) {
            return 0;
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Client oldItem, Client newItem) {
            return false;
        }

        @Override
        public boolean areItemsTheSame(Client item1, Client item2) {
            return false;
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
          notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
             notifyItemMoved(fromPosition, toPosition);
        }
    });

    Context context;
    NoteDayFragment noteDayFragment;
    DataBaseViewModel dataBaseViewModel;

    public ClientAdapter(Context context, NoteDayFragment noteDayFragment) {
        this.context = context;
        this.noteDayFragment = noteDayFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public ClienrAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SettingViewModel settingViewModel = new ViewModelProvider(noteDayFragment).get(SettingViewModel.class);
        return new ClienrAdapterViewHolder(settingViewModel.getViewInList(parent, viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ClienrAdapterViewHolder holder, int position) {
        Client client = clients.get(position);
        //ClientAddDataBase clientAddDataBase = ClientAddDataBase.getInstance(context);
        dataBaseViewModel = new ViewModelProvider(noteDayFragment).get(DataBaseViewModel.class);
        String hour_format = String.format("%02d", client.getTimeHour());
        String minute_format = String.format("%02d", client.getTimeMinute());
        holder.time_Hour.setText(hour_format);
        holder.time_Minute.setText(minute_format);
        holder.number.setText(client.getNumber_phone());
        holder.name.setText(client.getName());
        holder.id_in_bd.setText(""+client.getId());
        holder.job_client.setText(client.getJob());
        holder.deleteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delClient(holder.getAdapterPosition());
                //clientAddDataBase.getClientDAO().deleteClient(new Client(Long.parseLong(holder.id_in_bd.getText().toString())));
                dataBaseViewModel.databaseDeleteClient(holder.id_in_bd);
            }
        });

        holder.editClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("id_in_bd",holder.id_in_bd.getText().toString());
                bundle.putInt("dateID", NoteDayFragment.dataID);
                bundle.putString("name_client", holder.name.getText().toString() );
                bundle.putString("number_client", holder.number.getText().toString());
                bundle.putString("time_Hour", holder.time_Hour.getText().toString());
                bundle.putString("time_Minute", holder.time_Minute.getText().toString());
                bundle.putString("job_client", holder.job_client.getText().toString());
                EditClientFragment editClientFragment = new EditClientFragment();
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                editClientFragment.setArguments(bundle);
                editClientFragment.show(fragmentManager, "casual");
            }
        });

        holder.callClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + holder.number.getText().toString()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }



    public void delClient(int positionHolder) {
        clients.removeItemAt(positionHolder);
    }

    public void addListClient(List<Client> list){
        clients.addAll(list);
    }

    public void adapterReplace(List<Client> list){
        clients.replaceAll(list);
    }

    static class ClienrAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView time_Hour, time_Minute, name, number, id_in_bd, job_client;
        ImageView deleteClient, callClient, editClient;
        public ClienrAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            time_Hour = itemView.findViewById(R.id.timeHour);
            time_Minute = itemView.findViewById(R.id.timeMinute);
            name = itemView.findViewById(R.id.name_client);
            number = itemView.findViewById(R.id.number_phone_client);
            deleteClient = itemView.findViewById(R.id.imageDelete);
            id_in_bd = itemView.findViewById(R.id.id_in_bd);
            callClient = itemView.findViewById(R.id.imageCallClient);
            editClient = itemView.findViewById(R.id.imageEdit);
            job_client = itemView.findViewById(R.id.job_client);
        }
    }
}
