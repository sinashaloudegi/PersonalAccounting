package com.android.personalaccounting.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "transaction_table")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    long transactionId;

    @ForeignKey(entity = User.class, parentColumns = "transactionId", childColumns = "userId")
    private long userId;

    @ForeignKey(entity = Category.class, parentColumns = "transactionId", childColumns = "categoryId")
    private long categoryId;

    @ColumnInfo(name = "transactionAmount")
    private float amount;

    //TODO: Inorder to save this type of data you have to write a converterClass
    @Ignore
    private Date date;

    public Transaction(long transactionId, long userId, long categoryId, float amount) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;

    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
