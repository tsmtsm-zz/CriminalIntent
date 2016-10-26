package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by tsm on 2016/10/23.
 */
public class CrimeListFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private final static int REQUEST_CRIME=1;
    private static int mPostion=0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getUUID());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME) {
            // Handle result
        }
    }


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView1;
        public TextView mTextView2;
        public CheckBox mCheckBox;
        private Crime mCrime;





        public CrimeHolder(View itemview){
            super(itemview);
            itemview.setOnClickListener(this);
            mTextView1 = (TextView)itemview.findViewById(R.id.textview1);
            mTextView2= (TextView)itemview.findViewById(R.id.textview2);
            mCheckBox =(CheckBox)itemview.findViewById(R.id.solved_checkbox);
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCrime.setSolved(isChecked);
                }
            });

        }
        public void bindCrime(Crime crime){
            mCrime = crime;
            mTextView1.setText( mCrime.getTitle());
            mTextView2.setText( mCrime.getDate().toString());
            mCheckBox.setChecked(mCrime.isSolved());

        }

        @Override
        public void onClick(View v) {

            mPostion = CrimeLab.get(getActivity()).getPostion(mCrime.getUUID());



            Intent intent =CrimePagerActivity.newIntent(getActivity(),mCrime.getUUID());
           // Intent intent = new Intent(getActivity(),mCrime.getUUID());
           // startActivity(intent);
            startActivityForResult(intent,REQUEST_CRIME);
        }


    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
//            mPostion = position;
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return  v;
    }
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimies();

//        mCrimeAdapter = new CrimeAdapter(crimes);
//        mRecyclerView.setAdapter(mCrimeAdapter);
        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            mCrimeAdapter.notifyItemChanged(mPostion);

        }

    }
}
