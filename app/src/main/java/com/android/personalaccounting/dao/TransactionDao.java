package com.android.personalaccounting.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.personalaccounting.model.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Query("SELECT * FROM transaction_table")
    LiveData<List<Transaction>> getAllTransactions();


    //If the system upgraded to be used by many users
    @Query("SELECT * FROM transaction_table where userId = :userId")
    LiveData<List<Transaction>> getAllUserTransactions(long userId);

    @Query("DELETE FROM transaction_table")
    void deleteAllTransactions();


}
