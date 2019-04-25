package com.android.personalaccounting.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.personalaccounting.model.Transaction;
import com.android.personalaccounting.repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private static final String TAG = "TransactionViewModel";
    LiveData<List<Transaction>> allTransactions;
    private TransactionRepository mTransactionRepository;

    public TransactionViewModel(Application application) {
        super(application);
        mTransactionRepository = new TransactionRepository(application);
        allTransactions = mTransactionRepository.getAllTransactions();
    }


    public void insert(Transaction account) {
        mTransactionRepository.insert(account);
    }

    public void update(Transaction account) {
        mTransactionRepository.update(account);
    }

    public void delete(Transaction account) {
        mTransactionRepository.delete(account);
    }

    public void deleteAllTransactions() {
        mTransactionRepository.deleteAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }


    //Todo: implement this method if you want to get a user and return the correspondent transactions
  /*
    public LiveData<List<Transaction>> getAllUserTransactions(User userId) {
        return allTransactions;
    }

*/
}
