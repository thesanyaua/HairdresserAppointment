package com.example.hairdresserappointment.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdresserappointment.NoteDayFragment;
import com.example.hairdresserappointment.R;
import com.example.hairdresserappointment.other.DataInfo;

import java.util.List;

public class AdapterDayInfo extends RecyclerView.Adapter<AdapterDayInfo.AdapterDayInfoViewHolder> {

    List<DataInfo> dataInfos;

    public AdapterDayInfo(List<DataInfo> dataInfos) {
        this.dataInfos = dataInfos;
    }

    @NonNull
    @Override
    public AdapterDayInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDayInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.date_info_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDayInfoViewHolder holder, int position) {
       DataInfo dataInfo = dataInfos.get(position);
       holder.data_records.setText(dataInfo.getData());
       holder.numberOfRecords.setText(dataInfo.getNumberOfRecords());
       holder.day_name.setText(dataInfo.getDayName());
       holder.openDay.setText(""+dataInfo.getOpenDate());

       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Bundle bundleSetNoteDayFragment = new Bundle();
               bundleSetNoteDayFragment.putInt("dataID", Integer.parseInt(holder.openDay.getText().toString()));
               NoteDayFragment.dateClickInBar = holder.data_records.getText().toString();
               Navigation.findNavController(v).navigate(R.id.action_startFragment_to_noteDayFragment, bundleSetNoteDayFragment);
           }
       });

    }

    @Override
    public int getItemCount() {
        return dataInfos.size();
    }

    class AdapterDayInfoViewHolder extends RecyclerView.ViewHolder {
        TextView data_records;
        TextView numberOfRecords;
        TextView day_name;
        TextView openDay;
        CardView cardView;
        public AdapterDayInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            data_records = itemView.findViewById(R.id.data_info_startFragment);
            numberOfRecords = itemView.findViewById(R.id.numberOfRecords_textView);
            day_name = itemView.findViewById(R.id.dayName);
            openDay = itemView.findViewById(R.id.openDate);
            cardView = itemView.findViewById(R.id.cardView_open);

        }
    }
}
