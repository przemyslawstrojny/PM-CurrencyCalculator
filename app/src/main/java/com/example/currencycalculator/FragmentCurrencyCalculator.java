package com.example.currencycalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentCurrencyCalculator extends Fragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_currency_calculator, container, false);

        EditText quantity = (EditText) rootView.findViewById(R.id.quantity);
        TextView updateDate = (TextView) rootView.findViewById(R.id.text_last_update);
        TextView result = (TextView) rootView.findViewById(R.id.result);


        Button currencyFrom = (Button) rootView.findViewById(R.id.currency_from);
        currencyFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open activity with currency list
            }
        });


        Button currencyTo = (Button) rootView.findViewById(R.id.currency_to);
        currencyTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open activity with list of target currencies
            }
        });

        //Button to calculate the result
        Button calculateButton = (Button) rootView.findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO calculate function and set result TextView value
            }
        });

        //Button to update data. onClick button - get and parse data from nbp
        Button updateButton = (Button) rootView.findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO update data and set updateDate text view
            }
        });

        Button change = (Button) rootView.findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO replace value of button currencyFrom with currencyTo
            }
        });

        return rootView;
    }
}
