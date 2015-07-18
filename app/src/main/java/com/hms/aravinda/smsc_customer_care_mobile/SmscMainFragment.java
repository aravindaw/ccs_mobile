package com.hms.aravinda.smsc_customer_care_mobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aravinda on 14-Jul-15.
 */
public class SmscMainFragment extends Fragment {
    ;
    View rootview;
//    private String smscAvailable = "http://www.mocky.io/v2/55a71af4b2016cb0207e6f30";
    private String smscAvailable = "http://www.mocky.io/v2/55aab5d60958d9de0f7d528a";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.smsc_layout, container, false);

        View btnSmscSessionDetails = rootview.findViewById(R.id.getSmscs);
        btnSmscSessionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        System.out.println(s);
                        Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, s);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        smscList.setAdapter(dataAdapter);
                    }
                }).execute(smscAvailable);
            }
        });

        return rootview;
    }
}
