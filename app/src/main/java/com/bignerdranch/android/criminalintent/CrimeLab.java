package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tsm on 2016/10/22.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime>    mCrimies;

    public List<Crime> getCrimies() {
        return mCrimies;
    }
    public  Crime getCrime(UUID id){
        for (Crime crimie:mCrimies) {
            if (crimie.getUUID().equals(id)) {
                return crimie;
            }
        }
        return null;
    }
    public int getPostion(UUID id){
        int i=-1;
        for (Crime crimie:mCrimies) {
            i++;
            if (crimie.getUUID().equals(id)) {
                return i;
            }
        }
        return i;
    }



    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){

        mCrimies = new ArrayList<>();
        for (int i=0;i<100;i++){
            Crime crimie = new Crime();
            crimie.setTitle("Crime #"+i);
            crimie.setSolved(i % 2 == 0);
            mCrimies.add(crimie);
        }
    }
}
