package com.baraccasoftware.aaacolors.fragments;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baraccasoftware.aaacolors.R;
import com.baraccasoftware.aaacolors.model.Livello;
import com.baraccasoftware.aaacolors.model.LivelloUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {


    private static final long COUNT_DOWN_INTERVAL = 1000;
    private static final long MILLIS = 5 * 1000;
    public static final String TAG = "MatchFragment";
    private View rootview;
    private TextView time;
    private SimpleCountDown timer;
    private ImageView central, f,s,t;
    private Livello l;

    private OnInteractionMatchListener listener;
    private TextView ltextView;

    public MatchFragment() {
        // Required empty public constructor
    }

    public static MatchFragment getInstance(OnInteractionMatchListener listener){
        MatchFragment f = new MatchFragment();
        f.setListener(listener);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_match, container, false);
        time = (TextView) rootview.findViewById(R.id.time_textView);
        ltextView = (TextView) rootview.findViewById(R.id.level_textView2);
        central = (ImageView) rootview.findViewById(R.id.central_color_imageView);
        f = (ImageView) rootview.findViewById(R.id.f_color_imageView);
        s = (ImageView) rootview.findViewById(R.id.s_color_imageView);
        t = (ImageView) rootview.findViewById(R.id.t_color_imageView);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                if (l.checkFirstColor()) {
                    startNewLevel();
                } else {
                    resumeGameOver();
                }
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                if (l.checkSecondColor()) {
                    startNewLevel();
                } else {
                    resumeGameOver();
                }
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                if (l.checkThirdColor()) {
                    startNewLevel();
                } else {
                    resumeGameOver();
                }
            }
        });



        timer = new SimpleCountDown(getActivity(),MILLIS,COUNT_DOWN_INTERVAL,time);

        return rootview;
    }

    private void startNewLevel() {
        LivelloUtil.currentLevel++;
        listener.startNewLevel();
    }

    private void resumeGameOver(){
        listener.showGameOver();
    }

    public OnInteractionMatchListener getListener() {
        return listener;
    }

    public void setListener(OnInteractionMatchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPause() {
        super.onPause();
        try{
            timer.cancel();
            Log.i(TAG,"Stopped Timer on Pause");
        }catch (Exception e){
            Log.i(TAG,"stopped timer");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        l = LivelloUtil.getNext();


        play(l);
    }

    private void play(Livello l) {

        central.setImageDrawable(LivelloUtil.getTargetDrawable(getActivity(),l));
        f.setImageDrawable(LivelloUtil.getFirstDrawable(getActivity(),l));
        s.setImageDrawable(LivelloUtil.getSecondDrawable(getActivity(),l));
        t.setImageDrawable(LivelloUtil.getThirdDrawable(getActivity(),l));
       // ltextView.setText(getString(R.string.livello_q,l.getLiv()));
        timer.start();
    }

    public interface OnInteractionMatchListener {
        public void startNewLevel();
        public void showGameOver();
    }



    public class SimpleCountDown extends CountDownTimer {


        private final TextView target;
        private long millisInFuture, countDownInterval;
        private Context context;

        public SimpleCountDown(Context context, long millisInFuture, long countDownInterval, TextView target) {
            super(millisInFuture, countDownInterval);
            this.countDownInterval = countDownInterval;
            this.millisInFuture = millisInFuture;
            this.target = target;
            this.context = context;
        }

        @Override
        public void onTick(long l) {
            long r = l/countDownInterval;

            target.setText(String.valueOf(r));
            if(r<=3) target.setTextColor(context.getResources().getColor(R.color.red_danger));
        }

        @Override
        public void onFinish() {
            listener.showGameOver();
        }
    }


}
