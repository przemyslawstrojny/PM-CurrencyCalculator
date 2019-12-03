package com.example.currencycalculator;

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
import android.widget.ListView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentAnalytics extends Fragment {
    private Button currencyObserved;
    private SharedPreferences pref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_analytics, container, false);
        ListView listView = rootView.findViewById(R.id.list_to_analyse);

        pref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        this.getActivity().getPreferences(Context.MODE_PRIVATE);

        currencyObserved = (Button) rootView.findViewById(R.id.currency_observed);
        currencyObserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(),CurrencyListActivity.class),003);
            }
        });
        currencyObserved.setText(getPref("currencyObserved"));

        return rootView;
    }

    public void savePref(Button field, String key){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, field.getText().toString());
        editor.commit();
    }

    public String getPref(String key){
        return pref.getString(key, "");
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
        if(requestCode ==  3 && resultCode == RESULT_OK)
        {
            currencyObserved.setText(data.getStringExtra("message"));
            savePref(currencyObserved,"currencyObserved");
        }
    }
}
