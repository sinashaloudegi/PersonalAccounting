package com.android.personalaccounting;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.personalaccounting.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private List<Transaction> mTransactionList;
    private Context context;
    private static final String TAG = "TransactionAdapter";

    public TransactionAdapter(Context context) {
        this.mTransactionList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card_view, parent, false);
        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction currentTransaction = mTransactionList.get(position);


        holder.mTextView.setText(currentTransaction.getTransactionId() + " Test");

    }

    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }

    public void setTransactionList(List<Transaction> transactions) {
        this.mTransactionList = transactions;
        notifyDataSetChanged();
        Log.d(TAG, "setTransactionList: " + mTransactionList.size());
    }

    class TransactionHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        TransactionHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.transaction_amount_card);
        }
    }

}