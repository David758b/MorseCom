package com.example.morsecom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RecieveMessageFragment extends Fragment{


    TextView recieveTextview;
    EditText chatboxmessagefield;
    Button chatboxSend;
    SharedPreferences prefs;
    public View onCreateView(LayoutInflater i, ViewGroup Container, Bundle SavedInstanceState){

        View rod = i.inflate(R.layout.recievemessagefragment, Container, false);


        recieveTextview = rod.findViewById(R.id.testReceiveTextView);
        chatboxmessagefield = rod.findViewById(R.id.editChatboxmessage);
        chatboxSend = rod.findViewById(R.id.buttonChatboxsend);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        chatboxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieveTextview.setText(chatboxmessagefield.getText().toString());
                prefs.edit().putString("recieveMessage", chatboxmessagefield.getText().toString()).apply();
                chatboxmessagefield.setText("");
            }
        });

        return rod;
    }


    @Override
    public void onPause() {
        prefs.edit().putString("revieveMessage", "").apply();
        super.onPause();
    }
}
