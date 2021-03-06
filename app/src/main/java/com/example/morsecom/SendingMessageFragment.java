package com.example.morsecom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SendingMessageFragment extends Fragment{


    TextView sendingText, messageHeader, swipeRightGuide;
    SharedPreferences prefs;
    SharedPreferences.OnSharedPreferenceChangeListener changeListener;

    public View onCreateView(LayoutInflater i, ViewGroup Container, Bundle SavedInstanceState){

        View rod = i.inflate(R.layout.sendingmessage_fragment, Container, false);
        sendingText = rod.findViewById(R.id.sendingText);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("sendingMessage")){
                    sendingText.setText(prefs.getString("sendingMessage", ""));
                }
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(changeListener);
        return rod;
    }


}
