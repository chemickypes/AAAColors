package com.baraccasoftware.aaacolors;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baraccasoftware.aaacolors.fragments.GoalsFragment;


public class RankingActivity extends Activity {

    public static final java.lang.String FRAGMENT_TYPE = "type_fragment";
    public static final int GOALS_TYPE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        int f = getIntent().getExtras().getInt(FRAGMENT_TYPE,GOALS_TYPE);
        Fragment fragment;
        if(f == GOALS_TYPE){
             fragment = new GoalsFragment();
        }else {
            fragment = null;
        }

        getFragmentManager().beginTransaction()
                .add(R.id.container,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
