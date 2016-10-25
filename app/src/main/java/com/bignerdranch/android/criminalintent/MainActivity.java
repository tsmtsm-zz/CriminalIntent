package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;


public class MainActivity extends SingleFragmentActivity {

    public final static String CRIME_ID="crime_id";

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(CRIME_ID,id);
        return  intent;
    }

    @Override
   protected Fragment createFragment(){
        UUID id = (UUID)getIntent().getSerializableExtra(CRIME_ID);
        return CrimeFragment.newInstance(id);
    }
}
