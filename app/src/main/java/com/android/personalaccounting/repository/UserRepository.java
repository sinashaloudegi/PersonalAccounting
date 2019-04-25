package com.android.personalaccounting.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.personalaccounting.dao.UserDao;
import com.android.personalaccounting.database.PersonalAccountantDataBase;
import com.android.personalaccounting.model.User;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<User> user;

    public UserRepository(Application application) {
        PersonalAccountantDataBase db = PersonalAccountantDataBase.getInstance(application);
        mUserDao = db.mUserDao();
        user = mUserDao.getUser();
    }

    public void insert(User user) {
        new InsertNoteAsyncTask(mUserDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(mUserDao).execute(user);

    }

    public void delete(User user) {
        new DeleteNoteAsyncTask(mUserDao).execute(user);

    }


    public LiveData<User> getUser() {
        return user;
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mUserDao;

        private DeleteNoteAsyncTask(UserDao userDao) {
            this.mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.delete(users[0]);
            return null;
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        public InsertNoteAsyncTask(UserDao userDao) {
            this.mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        UserDao mUserDao;

        public UpdateUserAsyncTask(UserDao userDao) {
            this.mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.update(users[0]);
            return null;
        }
    }


}
