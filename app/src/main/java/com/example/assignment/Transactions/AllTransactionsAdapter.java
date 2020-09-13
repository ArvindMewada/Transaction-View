package com.example.assignment.Transactions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.assignment.Transactions.AllExpenses.AllExpensesFragment;
import com.example.assignment.Transactions.AllIncoming.AllIncomingFragment;

public class AllTransactionsAdapter extends FragmentPagerAdapter {

    int totalTabs;

    public AllTransactionsAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllExpensesFragment();
            case 1:
                return new AllIncomingFragment();
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
