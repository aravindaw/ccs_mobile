package com.hms.aravinda.smsc_customer_care_mobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aravinda on 14-Jul-15.
 */
public class SmscMainFragment extends Fragment {

    View rootview;
    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    //smsc list
    public String smscList = "http://www.mocky.io/v2/55b9182bacd73ab008ee2358";
    //smsc1
    public String smscDetails = "http://www.mocky.io/v2/55b8d0dbacd73a9702ee2305";
    //smsc2
    public String smscDetails2 = "http://www.mocky.io/v2/55b8d0ffacd73a9702ee2306";
    //smsc3
    public String smscDetails3 = "http://www.mocky.io/v2/55b8d120acd73a9c02ee2307";
    //smsc session details
    public String smscSessionDetails = "http://www.mocky.io/v2/55b82101f8bc809a1101c4bd";
    public String executeURL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.smsc_layout, container, false);

        mContentView = rootview.findViewById(R.id.smsc_linearLayout);
        mLoadingView = rootview.findViewById(R.id.loading_spinner);

        // Initially hide the content view.
        mContentView.setVisibility(View.GONE);

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        new AsyncTasksManager(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(List s) {
                Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, s);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                smscList.setAdapter(dataAdapter);
                showContentOrLoadingIndicator(true);
            }
        }).execute(smscList);
        View btnSmscDetails = rootview.findViewById(R.id.getSmscs);
        btnSmscDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContentOrLoadingIndicator(false);
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        Spinner smscList = (Spinner) rootview.findViewById(R.id.smscSelectionList);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, s);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        smscList.setAdapter(dataAdapter);
                        showContentOrLoadingIndicator(true);
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
                showContentOrLoadingIndicator(false);
                if (smscList.getSelectedItem().equals("smsc1")) {
                    executeURL = smscDetails;
                } else if (smscList.getSelectedItem().equals("smsc2")) {
                    executeURL = smscDetails2;
                } else if (smscList.getSelectedItem().equals("smsc3")) {
                    executeURL = smscDetails3;
                }
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        smscStates.setText((s.get(0).toString()));
                        activeSessions.setText((s.get(1).toString()));
                        throughtPut.setText((s.get(2).toString()));

                        showContentOrLoadingIndicator(true);
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
                showContentOrLoadingIndicator(false);
                new AsyncTasksManager(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(List s) {
                        ListView smscSessionList = (ListView) rootview.findViewById(R.id.smscSessionlistView);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, s);
                        smscSessionList.setAdapter(dataAdapter);
                        showContentOrLoadingIndicator(true);
                    }
                }).execute(smscSessionDetails);
            }
        });

        return rootview;
    }

    private void showContentOrLoadingIndicator(boolean contentLoaded) {
        // Decide which view to hide and which to show.
        final View showView = contentLoaded ? mContentView : mLoadingView;
        final View hideView = contentLoaded ? mLoadingView : mContentView;

        // Set the "show" view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        // Animate the "show" view to 100% opacity, and clear any animation listener set on
        // the view. Remember that listeners are not limited to the specific animation
        // describes in the chained method calls. Listeners are set on the
        // ViewPropertyAnimator object for the view, which persists across several
        // animations.
        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
        // to GONE as an optimization step (it won't participate in layout passes, etc.)
        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
    }
}
