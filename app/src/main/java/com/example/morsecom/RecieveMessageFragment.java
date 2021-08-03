package com.example.morsecom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class RecieveMessageFragment extends Fragment {

    EditText recieveText;
    TextView messeageRecieveHeader, swipeLeftGuide;

    public View onCreateView(LayoutInflater i, ViewGroup Container, Bundle SavedInstanceState){

        View rod = i.inflate(R.layout.recievingmessage_fragment, Container, false);
        recieveText = rod.findViewById(R.id.recievingText);
        messeageRecieveHeader = rod.findViewById(R.id.messageRecieveHeader);
        swipeLeftGuide = rod.findViewById(R.id.swipeLefttext);
        return rod;
    }
}
