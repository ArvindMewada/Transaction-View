package com.example.assignment.Transactions.AllIncoming;

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

import java.util.ArrayList;

import static com.example.assignment.Home.MainActivity.smsAndExpanseDataModelArrayList;

public class AllIncomingFragment extends Fragment {

    AllIncomingAdapter allIncomingAdapter;
    Context  context;
    RecyclerView recyclerView;
    ArrayList<SmsAndExpanseDataModel> listIncoming = new ArrayList<>();

    public AllIncomingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_all_incoming, container, false);
        initialization(v);
        adapterNotifyDataSetChanged();
        return v;
    }

    public  void initialization(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.all_incoming_rv);
        allIncomingAdapter = new AllIncomingAdapter(context, listIncoming);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(allIncomingAdapter);
    }

    public void adapterNotifyDataSetChanged() {
        listIncoming.clear();
        listIncoming.addAll(smsAndExpanseDataModelArrayList);
        allIncomingAdapter.notifyDataSetChanged();
    }

}