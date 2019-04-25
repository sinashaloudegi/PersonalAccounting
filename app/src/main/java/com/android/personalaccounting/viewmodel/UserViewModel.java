package com.android.personalaccounting.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.personalaccounting.model.User;
import com.android.personalaccounting.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    LiveData<User> user;
    private UserRepository mUserRepository;

    public UserViewModel(Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
        user = mUserRepository.getUser();
    }


    public void insert(User account) {
        mUserRepository.insert(account);
    }

    public void update(User account) {
        mUserRepository.update(account);
    }

    public void delete(User account) {
        mUserRepository.delete(account);
    }


    public LiveData<User> getUser() {
        return user;
    }
}
