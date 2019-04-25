package com.android.personalaccounting.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.personalaccounting.dao.TransactionDao;
import com.android.personalaccounting.dao.UserDao;
import com.android.personalaccounting.model.Category;
import com.android.personalaccounting.model.Transaction;
import com.android.personalaccounting.model.User;

//This class should be abstract, because we don't want to instantiate from it
@Database(entities = {Category.class, Transaction.class, User.class}, version = 3)
public abstract class PersonalAccountantDataBase extends RoomDatabase {
    private static final String TAG = "PersonalAccountantDataB";

    //Singleton- to make the database to be created only once.
    //Volatile is a kind of thread safe- it provides visibility to the variable, system does not cache this variable, it is always visible for evey threads
    private static volatile PersonalAccountantDataBase instance;

    public abstract UserDao mUserDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized PersonalAccountantDataBase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PersonalAccountantDataBase.class, "personal_account_database.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
            Log.d(TAG, "getInstance: afterIf");
        }

        return instance;
    }


    public abstract TransactionDao mTransactionDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao mUserDao;
        private TransactionDao mTransactionDao;

        private PopulateDbAsyncTask(PersonalAccountantDataBase db) {
            Log.d(TAG, "PopulateDbAsyncTask: in populate");
            mUserDao = db.mUserDao();
            mTransactionDao = db.mTransactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.insert(new User("Guest", null));
            Log.d(TAG, "doInBackground: Inserting...");
            return null;
        }
    }

}
