package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by tsm on 2016/10/23.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    private String TAG="CrimeListActivity";
    @Override
    protected Fragment createFragment() {
        Log.d(TAG,"Running createfragment");
        return new CrimeListFragment();
    }
}
