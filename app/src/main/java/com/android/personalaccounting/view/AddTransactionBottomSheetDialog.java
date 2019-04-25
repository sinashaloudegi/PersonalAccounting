package com.android.personalaccounting.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.android.personalaccounting.R;
import com.android.personalaccounting.model.Transaction;
import com.android.personalaccounting.viewmodel.TransactionViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddTransactionBottomSheetDialog extends BottomSheetDialogFragment {
    private CardView mAddAmountCardView;
    private CardView mTransactionTypeCardView;

    static AddTransactionBottomSheetDialog newInstance() {
        return new AddTransactionBottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_transactions, container, false);


        Button incomeBtn = view.findViewById(R.id.income_btn);
        mAddAmountCardView = view.findViewById(R.id.add_amount);
        mTransactionTypeCardView = view.findViewById(R.id.transaction_type);


//Todo: do something with this complicated animations
        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransactionViewModel viewModel = ViewModelProviders.of(getActivity()).get(TransactionViewModel.class);
                viewModel.insert(new Transaction(0, 0, 0, 1230.0f));
                mAddAmountCardView.animate()
                        .translationY(0)
                        .translationX(view.getWidth())
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mAddAmountCardView.setVisibility(View.GONE);
                                mTransactionTypeCardView.setVisibility(View.VISIBLE);
                                mTransactionTypeCardView.animate()
                                        .translationY(0)
                                        .translationX(0)
                                        .setDuration(300);
                            }
                        });
            }
        });
        return view;

    }
}
