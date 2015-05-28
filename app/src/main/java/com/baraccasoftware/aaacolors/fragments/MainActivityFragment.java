package com.baraccasoftware.aaacolors.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baraccasoftware.aaacolors.R;
import com.baraccasoftware.aaacolors.preferences.LevelPreferences;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MatchFragment.OnInteractionMatchListener, GameOverFragment.OnChangeRecordListener {

    private View rootview;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_main, container, false);

        rootview.findViewById(R.id.play_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMatchFragment();
            }
        });



        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        int record = LevelPreferences.getInstance(getActivity()).getRecord();
        String recordString = record == 0? getString(R.string.registra_nuovo_record):getString(R.string.record_text,record);

        ((TextView)rootview.findViewById(R.id.rercord_textView)).setText(recordString);
    }

    private void startMatchFragment() {
        getFragmentManager().beginTransaction()
                .add(R.id.container,MatchFragment.getInstance(this))
                .addToBackStack(MatchFragment.TAG)
                .commit();
    }

    @Override
    public void startNewLevel() {
        getFragmentManager().popBackStack();
        startMatchFragment();
    }

    @Override
    public void showGameOver() {
        getFragmentManager().popBackStack();
        startGameOverFragment();
    }

    private void startGameOverFragment() {
        //Toast.makeText(getActivity(),"GameOver",Toast.LENGTH_LONG).show();
        getFragmentManager().beginTransaction()
                .add(R.id.container, GameOverFragment.getInstance(this))
                .addToBackStack(GameOverFragment.TAG)
                .commit();
    }

    @Override
    public void onChangeRecord(int record) {
        ((TextView)rootview.findViewById(R.id.rercord_textView)).setText(getString(R.string.record_text,record));
    }
}
