package com.example.currencycalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.List;

public class FragmentCurrencyCalculator extends Fragment {
    View rootView;
    private Button currencyFrom;
    private Button currencyTo;
    private SharedPreferences pref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_currency_calculator, container, false);

        pref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        this.getActivity().getPreferences(Context.MODE_PRIVATE);

        EditText quantity = (EditText) rootView.findViewById(R.id.quantity);
        TextView updateDate = (TextView) rootView.findViewById(R.id.text_last_update);
        TextView result = (TextView) rootView.findViewById(R.id.result);


        currencyFrom = (Button) rootView.findViewById(R.id.currency_from);
        currencyFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(),CurrencyListActivity.class),1);
            }
        });
        currencyFrom.setText(getPref("currencyFrom"));


        currencyTo = (Button) rootView.findViewById(R.id.currency_to);
        currencyTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(),CurrencyListActivity.class),2);
            }
        });
        currencyTo.setText(getPref("currencyTo"));

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
        if(requestCode ==  1 && resultCode == Activity.RESULT_OK)
        {
            currencyFrom.setText(data.getStringExtra("message"));
            savePref(currencyFrom,"currencyFrom");
        }
        if(requestCode ==  2 && resultCode == Activity.RESULT_OK)
        {
            currencyTo.setText(data.getStringExtra("message"));
            savePref(currencyTo,"currencyTo");
        }
    }

    public void savePref(Button field, String key){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, field.getText().toString());
        editor.commit();
    }

    public String getPref(String key){
        return pref.getString(key, "");
    }
}
