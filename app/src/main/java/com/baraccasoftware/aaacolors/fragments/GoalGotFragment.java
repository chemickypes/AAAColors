package com.baraccasoftware.aaacolors.fragments;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.baraccasoftware.aaacolors.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoalGotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalGotFragment extends Fragment {
    public static final String TAG = "GoalGotFragment";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    private String mParam1;
    private String toPrint;
    private ObjectAnimator anim;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment GoalGotFragment.
     */
    public static GoalGotFragment newInstance(String param1) {
        GoalGotFragment fragment = new GoalGotFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public GoalGotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goal_got, container, false);

        toPrint = getString(R.string.you_are_n_player,mParam1);
        ((TextView)view.findViewById(R.id.message_textView)).setText(toPrint);

        view.findViewById(R.id.share_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });

        View panel = view.findViewById(R.id.goal_got_panel);

        anim = ObjectAnimator.ofFloat(panel, "scaleX", 0.0f, 1.0f);
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
    public void onResume() {
        super.onResume();
        anim.start();
    }

    private void share() {
        StringBuilder builder = new StringBuilder(toPrint);
        builder.append(" ");
        builder.append(getString(R.string.url_app));

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, builder.toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


}
