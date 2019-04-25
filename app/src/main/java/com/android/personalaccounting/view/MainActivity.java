package com.android.personalaccounting.view;


import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.personalaccounting.R;
import com.android.personalaccounting.TransactionAdapter;
import com.android.personalaccounting.model.Transaction;
import com.android.personalaccounting.model.User;
import com.android.personalaccounting.viewmodel.TransactionViewModel;
import com.android.personalaccounting.viewmodel.UserViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

//TODO: Check the implemented interfaces, one of them may be redundant
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton mAddTransactionFab;
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        TransactionViewModel transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        BottomAppBar bar = setActionBarAndGetBottomBar();
        findViews();
        ActionBarDrawerToggle toggle = getActionBarDrawerToggle(bar);
        //This used for the recyclerView of the transactions
        final TransactionAdapter mTransactionAdapter = new TransactionAdapter(this);
        setUpRecycleView(mTransactionAdapter);
        //Change the color of the Burger Button to White
        setBurgerBottonColor(toggle);
        mNavigationView.setNavigationItemSelectedListener(this);
        //Floating Action Button click listener for adding new transaction using AddTransactionBottomSheetDialog Fragment
        fabClickListener();
        //Observer of transactionViewModel.getAllTransactions
        observeGetAllTransactions(mTransactionAdapter, transactionViewModel);

        //Observer of userViewModel.getUser
        observeGetUser(userViewModel);


    }

    private void observeGetUser(UserViewModel userViewModel) {
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

            }
        });
    }

    private void fabClickListener() {
        mAddTransactionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTransactionBottomSheetDialog addTransactionBottomDialogFragment =
                        AddTransactionBottomSheetDialog.newInstance();
                addTransactionBottomDialogFragment.show(getSupportFragmentManager(),
                        "add_transaction_dialog_fragment");
            }
        });
    }

    private void observeGetAllTransactions(final TransactionAdapter mTransactionAdapter, TransactionViewModel transactionViewModel) {
        transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {

            @Override
            public void onChanged(List<Transaction> transactions) {
                mTransactionAdapter.setTransactionList(transactions);
            }
        });
    }

    private void setBurgerBottonColor(ActionBarDrawerToggle toggle) {
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
    }

    private ActionBarDrawerToggle getActionBarDrawerToggle(BottomAppBar bar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        return toggle;
    }

    private void setUpRecycleView(TransactionAdapter mTransactionAdapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mTransactionAdapter);
    }

    private BottomAppBar setActionBarAndGetBottomBar() {
        BottomAppBar bar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bar);
        return bar;
    }

    private void findViews() {
        mAddTransactionFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.show_transactions_recycler_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_drawer);
    }


    //if the drawer is open and the user press back button, this code will close the drawer first
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottomappbar_menu, menu);
        return true;
    }

    void showAboutDialog() {
        Dialog aboutDialog = new Dialog(this);
        aboutDialog.setContentView(R.layout.dialog_about);
        aboutDialog.setTitle("About");
        aboutDialog.getWindow().getAttributes().windowAnimations = R.style.AboutDialogAnimation;
        aboutDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                showAboutDialog();
                break;
            case R.id.alert:
                showAboutDialog();
                break;
            case R.id.chart:
                showAboutDialog();
                break;
            case R.id.export:
                showAboutDialog();
                break;
        }
        return true;
    }
}
