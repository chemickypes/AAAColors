package com.baraccasoftware.aaacolors.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baraccasoftware.aaacolors.R;
import com.baraccasoftware.aaacolors.model.LivelloUtil;
import com.baraccasoftware.aaacolors.preferences.LevelPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalsFragment extends Fragment implements View.OnClickListener {


    private View rootview;
    private int nMatches = 0;

    public GoalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_goals, container, false);


        LevelPreferences preferences = LevelPreferences.getInstance(getActivity());
        nMatches = preferences.getNumberOfColor();

        ((TextView) rootview.findViewById(R.id.number_matches_textView)).setText(
                getString(R.string.you_match_n_color, nMatches)
        );

        View y = rootview.findViewById(R.id.young_textView);
        if(preferences.getGoals(LevelPreferences.YOUNG_L_TAG)) select(y);
        setBehaviour(y,LivelloUtil.YOUNG);


        View e = rootview.findViewById(R.id.expert_textView);
        if(preferences.getGoals(LevelPreferences.EXPERT_L_TAG)) select(e);
        setBehaviour(e,LivelloUtil.EXPERT);

        View s = rootview.findViewById(R.id.senior_textView);
        if(preferences.getGoals(LevelPreferences.SENIOR_L_TAG)) select(s);
        setBehaviour(s,LivelloUtil.SENIOR);

        View l = rootview.findViewById(R.id.leader_textView);
        if(preferences.getGoals(LevelPreferences.LEADER_L_TAG)) select(l);
        setBehaviour(l,LivelloUtil.LEADER);

        View b = rootview.findViewById(R.id.boss_textView);
        if(preferences.getGoals(LevelPreferences.BOSS_L_TAG)) select(b);
       setBehaviour(b,LivelloUtil.BOSS);

        View k = rootview.findViewById(R.id.king_textView);
        if(preferences.getGoals(LevelPreferences.KING_L_TAG)) select(k);
        setBehaviour(k, LivelloUtil.KING);

        return rootview;
    }

    private void select(View k){
        k.setBackgroundColor(getResources().getColor(R.color.green_500));
        ((TextView)k).setTextColor(getResources().getColor(android.R.color.white));
    }

    private void setBehaviour(View k,int goal){
        k.setTag(goal);
        k.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),
                getString(R.string.you_must_match_n_color,(Integer)view.getTag()),Toast.LENGTH_LONG).show();
    }
}
