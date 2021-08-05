package com.example.morsecom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RecieveMessageFragment extends Fragment {


    TextView recieveTextview;
    EditText chatboxmessagefield;
    Button chatboxSend;

    public View onCreateView(LayoutInflater i, ViewGroup Container, Bundle SavedInstanceState){

        View rod = i.inflate(R.layout.testlayout, Container, false);


        recieveTextview = rod.findViewById(R.id.testReceiveTextView);
        chatboxmessagefield = rod.findViewById(R.id.editChatboxmessage);
        chatboxSend = rod.findViewById(R.id.buttonChatboxsend);

        return rod;
    }
}
