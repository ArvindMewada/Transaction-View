package com.example.assignment.Home;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Model.SmsAndExpanseDataModel;
import com.example.assignment.R;
import com.example.assignment.Transactions.AllTransactionsActivity;
import com.example.assignment.Utils.Utils;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<SmsAndExpanseDataModel> smsAndExpanseDataModelArrayList = new ArrayList<SmsAndExpanseDataModel>();
    ProgressDialog progressDialog;
    PieChart pieChart;
    ArrayList<Integer> listExpense = new ArrayList<>();
    ArrayList<Integer> listIncome = new ArrayList<>();
    int incomingTotal = 0;
    int expenseTotal = 0;
    Button relativeLayout;
    TextView totalIncoming, totalExpenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //method for initialisation
        initialization();
        readAllTransactionsSms();
        setOnClickListener();
    }

    private void initialization() {
        smsAndExpanseDataModelArrayList.clear();
        pieChart = findViewById(R.id.pieChart);
        totalExpenses = findViewById(R.id.tv_expenses);
        totalIncoming = findViewById(R.id.tv_incoming);
        relativeLayout = findViewById(R.id.all_transaction);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading sms");
    }

    public void readAllTransactionsSms() {
        smsAndExpanseDataModelArrayList.clear();
        ContentResolver contentResolver = getContentResolver();
        Cursor c = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        progressDialog.show();
        // Read the sms data
        assert c != null;
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String mobile = c.getString(c.getColumnIndexOrThrow("address"));
                String message = c.getString(c.getColumnIndexOrThrow("body"));
                String date = c.getString(c.getColumnIndex("date"));
                SmsAndExpanseDataModel smsAndExpanseDataModel = new SmsAndExpanseDataModel();
                smsAndExpanseDataModel.setDate(date);
                smsAndExpanseDataModel.setMessage(message);
                smsAndExpanseDataModel.setMobile(mobile);
                smsAndExpanseDataModelArrayList.add(smsAndExpanseDataModel);
                c.moveToNext();
            }
            c.close();
            progressDialog.dismiss();
        }
        actualSmsDataSet();
    }


    private void actualSmsDataSet() {
        listExpense.clear();
        listIncome.clear();
        for (int i = 0; i < smsAndExpanseDataModelArrayList.size(); i++) {
            SmsAndExpanseDataModel smsDto = smsAndExpanseDataModelArrayList.get(i);
            String all = smsDto.getMessage();
            if (all.contains("debited") || all.contains("purchasing")
                    || all.contains("withdrawn") || all.contains("purchase")
                    || all.contains("dr")) {
                // Find instance of pattern matches
                Matcher m = new Utils().patternFun().matcher(smsDto.getMessage());
                if (m.find()) {
                    String s = m.group().replaceAll("\\D", "");
                    listExpense.add(Integer.valueOf(s));
                } else {
                    Log.e("No_matchedValue ", "No_matchedValue ");
                }

            } else if (all.contains("credited") || all.contains("cr")) {
                // Find instance of pattern matches
                Matcher m = new Utils().patternFun().matcher(smsDto.getMessage());
                if (m.find()) {
                    String sum = m.group().replaceAll("\\D", "");
                    listIncome.add(Integer.valueOf(sum));
                } else {
                    Log.e("No_matchedValue ", "No_matchedValue ");
                }
            }

        }
        setDataTotal(listIncome, listExpense);
    }

    public void setDataTotal(ArrayList<Integer> listIncome, ArrayList<Integer> listExpense) {
        //        for loop use
        for (int d = 0; d < listIncome.size(); d++) {
            incomingTotal += listIncome.get(d);
        }
        //   for each loop use
        for (Integer d : listExpense) {
            expenseTotal += d;
        }
        totalExpenses.setText(expenseTotal + "");
        totalIncoming.setText(incomingTotal + "");
        pieDataSetFun("Expense Label", expenseTotal, Color.parseColor("#E9967A"));
        pieDataSetFun("Incoming Label", incomingTotal, Color.parseColor("#008000"));
        pieChart.startAnimation();
    }


    public void setOnClickListener() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllTransactionsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void pieDataSetFun(String labelName, int valueShow, int color) {
        pieChart.addPieSlice(
                new PieModel(
                        labelName,
                        valueShow,
                        color));


    }


}

