package com.baraccasoftware.aaacolors.fragments;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baraccasoftware.aaacolors.R;
import com.baraccasoftware.aaacolors.model.LivelloUtil;
import com.baraccasoftware.aaacolors.preferences.LevelPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverFragment extends Fragment {


    public static final String TAG = "GameOverFramgnet";
    int record;
    private OnChangeRecordListener listener;
    private LinearLayout panel;
    private ObjectAnimator anim;

    public GameOverFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        record = LevelPreferences.getInstance(activity).getRecord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_over, container, false);
        panel = (LinearLayout) view.findViewById(R.id.panel_game_over);
        TextView message = (TextView) view.findViewById(R.id.message_textView);
        TextView numberMatches =  (TextView) view.findViewById(R.id.matchs_textView);
        View share = view.findViewById(R.id.share_textView);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });

        if(LivelloUtil.currentLevel>record){
            record = LivelloUtil.currentLevel;
            LevelPreferences.getInstance(getActivity()).setRecord(LivelloUtil.currentLevel);


            message.setText(R.string.new_record);
            message.setTextColor(getResources().getColor(R.color.green_500));
            numberMatches.setText(getString(R.string.you_match_n_color,record));
            listener.onChangeRecord(record);
        }else {
            message.setText(R.string.game_over);
            message.setTextColor(getResources().getColor(R.color.red_danger));
            numberMatches.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
        }

        LivelloUtil.currentLevel = 0;

        anim
                = ObjectAnimator.ofFloat(panel, "scaleX", 0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setInterpolator(new BounceInterpolator());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //panel.animate().alpha(0).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        //panel.animate().yBy(0).setDuration(10000).setInterpolator(new BounceInterpolator()).setStartDelay(200);
        anim.start();
    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message,record));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public static Fragment getInstance(OnChangeRecordListener listener) {
        GameOverFragment f = new GameOverFragment();
        f.setListener(listener);
        return f;
    }

    public void setListener(OnChangeRecordListener listener) {
        this.listener = listener;
    }

    public interface OnChangeRecordListener {
        void onChangeRecord(int record);
    }
}
