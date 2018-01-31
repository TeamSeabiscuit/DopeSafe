package com.uniting.android.msic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

/**
 * Created by jeeppeck on 1/30/18.
 */

public class TimeStep extends Fragment implements Step {

    private final static String TAG = "TimeStep";

    NumberPicker numberPicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.time_step, container, false);
        numberPicker = v.findViewById(R.id.time_picker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setValue(2);
        return v;
    }

    @Override
    public VerificationError verifyStep() {
        Prefs.setTime(getContext(), numberPicker.getValue());
        Prefs.setSetupComplete(getContext(), true);
        startMainActivity();
        return null;
    }

    @Override
    public void onSelected(){
    }

    @Override
    public void onError(@NonNull VerificationError error){
        //handle error
    }

    private void startMainActivity() {
        Log.d(TAG, "starting main activity");
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}