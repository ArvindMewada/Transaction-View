package com.example.assignment.Transactions.AllExpenses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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


public class AllExpensesAdapter extends RecyclerView.Adapter<AllExpensesAdapter.MyViewHolder> {
    private ArrayList<SmsAndExpanseDataModel> smsAndExpanseDataModelArrayList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, title, type;
        ImageView typeIV;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_mobile);
            amount = (TextView) view.findViewById(R.id.tv_amount);
            date = (TextView) view.findViewById(R.id.tv_date);
            typeIV = (ImageView) view.findViewById(R.id.iv_type);
            type = (TextView) view.findViewById(R.id.tv_type);


        }
    }

    public AllExpensesAdapter(Context context, ArrayList<SmsAndExpanseDataModel> smsAndExpanseDataModelArrayList) {
        this.smsAndExpanseDataModelArrayList = smsAndExpanseDataModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_all_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SmsAndExpanseDataModel smsAndExpanseDataModel = smsAndExpanseDataModelArrayList.get(position);
        if(smsAndExpanseDataModel != null && smsAndExpanseDataModel.getMessage() != null
                && smsAndExpanseDataModel.getType() != null && smsAndExpanseDataModel.getMobile() != null
                && smsAndExpanseDataModel.getDate() != null){
            holder.title.setText(smsAndExpanseDataModel.getMobile());
            setDate(holder, smsAndExpanseDataModel.getDate());
            setType(holder, smsAndExpanseDataModel.getType(), smsAndExpanseDataModel.getMessage());
        }

    }

    @SuppressLint("SetTextI18n")
    public void setType(MyViewHolder myViewHolder, String type, String amount) {
        if (type != null && type.equalsIgnoreCase("debit")) {
            myViewHolder.typeIV.setImageResource(R.drawable.expenses);
            myViewHolder.type.setTextColor(Color.parseColor("#E9967A"));
            myViewHolder.type.setText("Debit");
            myViewHolder.amount.setText("\u20B9 " + amount);
            myViewHolder.amount.setTextColor(Color.parseColor("#E9967A"));
        } else {
            myViewHolder.typeIV.setImageResource(R.drawable.incoming);
            myViewHolder.type.setTextColor(Color.parseColor("#008000"));
            myViewHolder.type.setText("Credited");
            myViewHolder.amount.setText("\u20B9 " + amount);
            myViewHolder.amount.setTextColor(Color.parseColor("#008000"));
        }
    }

    public void setDate(MyViewHolder myViewHolder, String date) {
        if(date != null && date.toString().length() > 0){
            long timestamp = Long.parseLong(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            if(calendar != null && calendar.getTime() != null){
                Date finalDate = calendar.getTime();
                if(finalDate != null && finalDate.toString().length() > 0){
                    String smsDate = finalDate.toString();
                   String a = smsDate.substring(0, smsDate.length()-24);
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
