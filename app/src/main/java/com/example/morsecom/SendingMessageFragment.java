package com.example.morsecom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SendingMessageFragment extends Fragment {


    TextView sendingText, messageHeader, swipeRightGuide;

    public View onCreateView(LayoutInflater i, ViewGroup Container, Bundle SavedInstanceState){

        View rod = i.inflate(R.layout.sendingmessage_fragment, Container, false);
        messageHeader = rod.findViewById(R.id.messageHeader);
        sendingText = rod.findViewById(R.id.sendingText);
        swipeRightGuide = rod.findViewById(R.id.swiperighttext);
        return rod;
    }

}
