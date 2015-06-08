package com.baraccasoftware.aaacolors.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baraccasoftware.aaacolors.R;
import com.baraccasoftware.aaacolors.RankingActivity;
import com.baraccasoftware.aaacolors.model.LivelloUtil;
import com.baraccasoftware.aaacolors.preferences.LevelPreferences;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MatchFragment.OnInteractionMatchListener, GameOverFragment.OnChangeRecordListener {

    private View rootview;
    private int levelGot = -1;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                reloadData();
            }
        }).start();
    }

    private void reloadData() {
        LevelPreferences p = LevelPreferences.getInstance(getActivity());
        int record = p.getRecord();
        int nMa = p.getNumberOfColor();
        if(nMa == 0 && record>0) p.incrementNumberOfColor(record);

       //setto tuttii traguardi
        if(record >=10) p.logGoals(LevelPreferences.YOUNG_L_TAG);
        if(record >=50) p.logGoals(LevelPreferences.EXPERT_L_TAG);
        if(record >=70) p.logGoals(LevelPreferences.SENIOR_L_TAG);
        if(record >=100) p.logGoals(LevelPreferences.LEADER_L_TAG);
        if(record >=150) p.logGoals(LevelPreferences.BOSS_L_TAG);
        if(record >=200) p.logGoals(LevelPreferences.KING_L_TAG);

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

        rootview.findViewById(R.id.trofei_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrofeiActivity();
            }
        });



        return rootview;
    }

    private void startTrofeiActivity() {
        Intent intent = new Intent(getActivity(), RankingActivity.class);
        Bundle b = new Bundle();
        b.putInt(RankingActivity.FRAGMENT_TYPE,RankingActivity.GOALS_TYPE);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainActivityFragment","OnResume()");
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

        boolean[] goalsN = LivelloUtil.getGoals(record);
        boolean[] goalsP = LevelPreferences.getInstance(getActivity()).getGoals();
        //TODO

        levelGot = 5;
        //decremento il livello raggiunto se i livelli raggiunti e che avevo sono uguali
        //e se quelli nuovi sono falsi
        //while (levelGot >-2 && (goalsN[levelGot] == goalsP[levelGot] || (!goalsN[levelGot] && goalsP[levelGot]))) levelGot--;
        while (levelGot >-1 && goalsN[levelGot] == goalsP[levelGot]) levelGot--;
        //levelGOt sarà l'indice del livello raggiunto



    }

    @Override
    public void onGoalGot() {
       Log.d("MainActivityFragment","onGoalGot "+ levelGot);

        if(levelGot>-1){
            LevelPreferences.getInstance(getActivity()).logGoals(levelGot);
            String[] levelString = getResources().getStringArray(R.array.level_player);

            getFragmentManager().beginTransaction()
                    .add(R.id.container,GoalGotFragment.newInstance(levelString[levelGot]))
                    .addToBackStack(GoalGotFragment.TAG)
                    .commit();
            //Toast.makeText(getActivity(),"YouGetNewLevet",Toast.LENGTH_SHORT).show();
        }
    }
}
