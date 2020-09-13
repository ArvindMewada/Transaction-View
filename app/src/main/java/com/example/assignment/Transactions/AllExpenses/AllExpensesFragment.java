package com.example.assignment.Transactions.AllExpenses;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Model.SmsAndExpanseDataModel;
import com.example.assignment.R;
import com.example.assignment.Utils.Utils;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static com.example.assignment.Home.MainActivity.smsAndExpanseDataModelArrayList;


public class AllExpensesFragment extends Fragment {
    RecyclerView recyclerView;
    AllExpensesAdapter allExpensesAdapter;
    ArrayList<SmsAndExpanseDataModel> listExpense = new ArrayList<>();
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_all_expanse, container, false);
        initialization(v);
        setNotifyDataSetChanged();
        return v;
    }


    public void initialization(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.all_expense_rv);
        allExpensesAdapter = new AllExpensesAdapter(context, listExpense);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(allExpensesAdapter);
    }

    public void setNotifyDataSetChanged() {
        listExpense.clear();
        for (int i = 0; i < smsAndExpanseDataModelArrayList.size(); i++) {
            SmsAndExpanseDataModel smsDto = smsAndExpanseDataModelArrayList.get(i);
            String all = smsDto.getMessage();
            if (all.contains("debited")
                    || all.contains("withdrawn")) {
                SmsAndExpanseDataModel smsAndExpanseDataModel = new SmsAndExpanseDataModel();
                smsAndExpanseDataModel.setType("debit");
                Matcher m = new Utils().patternFun().matcher(smsDto.getMessage());
                if (m.find()) {
                    String s = m.group().replaceAll("\\D", "");
                    smsAndExpanseDataModel.setMessage(s);
                    smsAndExpanseDataModel.setMobile(smsDto.getMobile());
                    smsAndExpanseDataModel.setDate(smsDto.getDate());
                    listExpense.add(smsAndExpanseDataModel);
                }
            }else if(all.contains("credited") || all.contains("cr")){
                SmsAndExpanseDataModel smsAndExpanseDataModel = new SmsAndExpanseDataModel();
                smsAndExpanseDataModel.setType("credited");
                Matcher m = new Utils().patternFun().matcher(smsDto.getMessage());
                if (m.find()) {
                    String s = m.group().replaceAll("\\D", "");
                    smsAndExpanseDataModel.setMessage(s);
                    smsAndExpanseDataModel.setMobile(smsDto.getMobile());
                    smsAndExpanseDataModel.setDate(smsDto.getDate());
                    listExpense.add(smsAndExpanseDataModel);
                }
            }
        }
        allExpensesAdapter.notifyDataSetChanged();
    }

}