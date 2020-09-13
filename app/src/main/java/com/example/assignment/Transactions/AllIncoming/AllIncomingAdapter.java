package com.example.assignment.Transactions.AllIncoming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Model.SmsAndExpanseDataModel;
import com.example.assignment.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AllIncomingAdapter extends RecyclerView.Adapter<AllIncomingAdapter.MyViewHolder> {
    private ArrayList<SmsAndExpanseDataModel> smsAndExpanseDataModelArrayList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, title;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_mobile);
            amount = (TextView) view.findViewById(R.id.tv_message);
            date = (TextView) view.findViewById(R.id.tv_date);
            imageView = (ImageView) view.findViewById(R.id.iv_type);

        }
    }

    public AllIncomingAdapter(Context context, ArrayList<SmsAndExpanseDataModel> smsAndExpanseDataModelArrayList) {
        this.smsAndExpanseDataModelArrayList = smsAndExpanseDataModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_all_incoming_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SmsAndExpanseDataModel smsAndExpanseDataModel = smsAndExpanseDataModelArrayList.get(position);
        if (smsAndExpanseDataModel.getDate() != null && smsAndExpanseDataModel.getMessage() != null
                && smsAndExpanseDataModel.getMobile() != null) {
            holder.title.setText(smsAndExpanseDataModel.getMobile());
            holder.amount.setText(smsAndExpanseDataModel.getMessage());
            setDate(holder, smsAndExpanseDataModel.getDate());
        }

    }

    public void setDate(MyViewHolder myViewHolder, String date) {
        if (date != null && date.toString().length() > 0) {
            long timestamp = Long.parseLong(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            if (calendar != null && calendar.getTime() != null) {
                Date finalDate = calendar.getTime();
                if (finalDate != null && finalDate.toString().length() > 0) {
                    String smsDate = finalDate.toString();
                    String a = smsDate.substring(0, smsDate.length() - 24);
                    myViewHolder.date.setText(a);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return smsAndExpanseDataModelArrayList.size();
    }

}
