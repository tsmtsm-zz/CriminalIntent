package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by tsm on 2016/10/21.
 */
public class CrimeFragment extends Fragment {

    private Crime mCrimie;
    private EditText mTitleField;
    private CheckBox mCheckBox;
    private Button mDateButton;
    private final static String ARG_CRIME_ID="crime_id";

    public static CrimeFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,id);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return  crimeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        UUID id =(UUID) getActivity().getIntent().getSerializableExtra(MainActivity.CRIME_ID);
        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        mCrimie = CrimeLab.get(getActivity()).getCrime(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime,container,false);



        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrimie.getDate().toString());
        mDateButton.setEnabled(false);

        mCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mCheckBox.setChecked(mCrimie.isSolved());

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrimie.setSolved(isChecked);
            }
        });
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrimie.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrimie.setTitle(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return  v;
    }
}
