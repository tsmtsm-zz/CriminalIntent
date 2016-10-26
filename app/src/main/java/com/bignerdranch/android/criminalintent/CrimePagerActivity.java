package com.bignerdranch.android.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by tsm on 2016/10/25.
 */

public class CrimePagerActivity  extends FragmentActivity{
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    public final static String CRIME_ID="crime_id";

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context,CrimePagerActivity.class);
        intent.putExtra(CRIME_ID,id);
        return  intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view);

        mCrimes = CrimeLab.get(this).getCrimies();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getUUID());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        UUID crime_id = (UUID)getIntent().getSerializableExtra(CRIME_ID);
        mViewPager.setCurrentItem(CrimeLab.get(this).getPostion(crime_id));

    }
}
