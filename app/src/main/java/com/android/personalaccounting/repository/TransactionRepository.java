package com.android.personalaccounting.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.personalaccounting.dao.TransactionDao;
import com.android.personalaccounting.database.PersonalAccountantDataBase;
import com.android.personalaccounting.model.Transaction;

import java.util.List;

public class TransactionRepository {
    private TransactionDao mTransactionDao;
    LiveData<List<Transaction>> transactions;

    public void setTransactions(LiveData<List<Transaction>> transactions) {
        this.transactions = transactions;
    }

    public TransactionRepository(Application application) {
        PersonalAccountantDataBase db = PersonalAccountantDataBase.getInstance(application);
        mTransactionDao = db.mTransactionDao();

    }

    public void insert(Transaction transaction) {
        new InsertTransactionAsyncTask(mTransactionDao).execute(transaction);
    }

    public void update(Transaction transaction) {
        new UpdateTransactionAsyncTask(mTransactionDao).execute(transaction);

    }

    public void delete(Transaction transaction) {
        new DeleteTransactionAsyncTask(mTransactionDao).execute(transaction);

    }

    public void deleteAllTransactions() {
        new DeleteAllTransactionsAsyncTask(mTransactionDao).execute();

    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return mTransactionDao.getAllTransactions();

    }

    public LiveData<List<Transaction>> getAllUserTransactions(long userId) {
        return mTransactionDao.getAllUserTransactions(userId);

    }


    private static class DeleteTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao mTransactionDao;

        private DeleteTransactionAsyncTask(TransactionDao transactionDao) {
            this.mTransactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            mTransactionDao.delete(transactions[0]);
            return null;
        }
    }

    private static class InsertTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {
        private TransactionDao mTransactionDao;

        public InsertTransactionAsyncTask(TransactionDao transactionDao) {
            this.mTransactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            mTransactionDao.insert(transactions[0]);
            return null;
        }
    }

    private static class UpdateTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {
        TransactionDao mTransactionDao;

        public UpdateTransactionAsyncTask(TransactionDao transactionDao) {
            this.mTransactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            mTransactionDao.update(transactions[0]);
            return null;
        }
    }

    private static class DeleteAllTransactionsAsyncTask extends AsyncTask<Void, Void, Void> {

        TransactionDao mTransactionDao;

        public DeleteAllTransactionsAsyncTask(TransactionDao transactionDao) {
            mTransactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTransactionDao.deleteAllTransactions();
            return null;
        }
    }


}
