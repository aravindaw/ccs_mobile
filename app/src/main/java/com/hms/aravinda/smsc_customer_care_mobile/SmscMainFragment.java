package com.hms.aravinda.smsc_customer_care_mobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aravinda on 14-Jul-15.
 */
public class SmscMainFragment extends Fragment {
    ;
    View rootview;
    //    private String smscAvailable = "http://www.mocky.io/v2/55a71af4b2016cb0207e6f30";
    private String smscAvailable = "http://www.mocky.io/v2/55aab5d60958d9de0f7d528a";
    private String smscSessionAvailable = "http://www.mocky.io/v2/55b3bfb61c5bf14e07c90073";
    private String smscList = "http://www.mocky.io/v2/55b5cff87f8e70e7165d9590";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.smsc_layout, container, false);

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
