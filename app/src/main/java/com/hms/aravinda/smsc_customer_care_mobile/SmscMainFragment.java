package com.hms.aravinda.smsc_customer_care_mobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aravinda on 14-Jul-15.
 */
public class SmscMainFragment extends Fragment {

    View rootview;
    public String smscSessionAvailable = "http://www.mocky.io/v2/55b3bfb61c5bf14e07c90073";
    public String fullSmscDetailList = "http://www.mocky.io/v2/55b675b176961dee0d7c5b71";
    public String smscList = "http://www.mocky.io/v2/55b5cff87f8e70e7165d9590";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.smsc_layout, container, false);

        new AsyncTasksManager(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(List s) {
                Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, s);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                smscList.setAdapter(dataAdapter);
            }
        }).execute(smscList);
        View btnSmscDetails = rootview.findViewById(R.id.getSmscs);
        btnSmscDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, s);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        smscList.setAdapter(dataAdapter);
                    }
                }).execute(smscList);

                final TextView smscStates = (TextView) rootview.findViewById(R.id.smscStatusView);
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {

                        smscStates.setText("Hellooo...");
                    }
                }).execute(fullSmscDetailList);
            }
        });

//        final Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
//        final TextView smscStates = (TextView) rootview.findViewById(R.id.smscStatusView);
//        if (smscStates.getText().toString().equals("")) {
//            new AsyncTasksManager(new OnTaskCompleted() {
//                @Override
//                public void onTaskCompleted(List s) {
//
//                    smscStates.setText((smscList.getSelectedItem()).toString());
//                }
//            }).execute(fullSmscDetailList);
//        }


        final Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
        final TextView smscStates = (TextView) rootview.findViewById(R.id.smscStatusView);
        smscList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {

                        smscStates.setText((smscList.getSelectedItem()).toString());
                    }
                }).execute(fullSmscDetailList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {

                        smscStates.setText((smscList.getSelectedItem()).toString());
                    }
                }).execute(fullSmscDetailList);
            }
        });


        View btnSmscSessionDetails = rootview.findViewById(R.id.getSmscSessionDetails);
        btnSmscSessionDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        ListView smscSessionList = (ListView) rootview.findViewById(R.id.smscSessionlistView);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, s);
                        smscSessionList.setAdapter(dataAdapter);
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                        dataAdapter.add("foo");
                        dataAdapter.add("foo2");
                    }
                }).execute(smscSessionAvailable);
            }
        });
        return rootview;
    }
}
