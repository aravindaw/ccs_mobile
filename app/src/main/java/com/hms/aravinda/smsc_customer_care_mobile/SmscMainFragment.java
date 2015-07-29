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
    public String smscList = "http://www.mocky.io/v2/55b5cff87f8e70e7165d9590";
    //smsc1
    public String smscDetails = "http://www.mocky.io/v2/55b7bd91f8bc80da0901c488";
    //smsc2
    public String smscDetails2 = "http://www.mocky.io/v2/55b7ca1cf8bc80f40a01c48c";
    //smsc3
    public String smscDetails3 = "http://www.mocky.io/v2/55b7ca72f8bc80fd0a01c48d";
    public String smscSessionDetails = "http://www.mocky.io/v2/55b82101f8bc809a1101c4bd";
    public String executeURL;

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
            }
        });

        final Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
        final TextView smscStates = (TextView) rootview.findViewById(R.id.smscStatusView);
        final TextView activeSessions = (TextView) rootview.findViewById(R.id.smscActiveSessView);
        final TextView throughtPut = (TextView) rootview.findViewById(R.id.smscThroughputView);
        smscList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (smscList.getSelectedItem().equals("smsc1")) {
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$1");
                    executeURL = smscDetails;
                } else if (smscList.getSelectedItem().equals("smsc2")) {
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$2");
                    executeURL = smscDetails2;
                } else if (smscList.getSelectedItem().equals("smsc3")) {
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$3");
                    executeURL = smscDetails3;
                }
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
//                        smscStates.setText((s.get(0).toString()));
//                        activeSessions.setText((s.get(1).toString()));
//                        throughtPut.setText((s.get(2).toString()));
                    }
                }).execute(executeURL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                smscStates.setText((smscList.getSelectedItem()).toString());
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
                    }
                }).execute(smscSessionDetails);
            }
        });
        return rootview;
    }
}
