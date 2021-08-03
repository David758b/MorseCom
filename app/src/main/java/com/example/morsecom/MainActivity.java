package com.example.morsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * https://developer.android.com/guide/input */
public class MainActivity extends AppCompatActivity {

    //todo skal ikke være her
    TextView sendingText;

    ViewPager viewPager;
    InputDevice switchJoyCon;
    KeyEventManager keyEventManager;
    Fragment recivefragment, sendfragment;


    // todo --> apparently i could not find a way to make the controller it self vibrate, so
    //  i will just make the phone vibrate
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        recivefragment = new RecieveMessageFragment();
        sendfragment = new SendingMessageFragment();
      //  messageHeader = findViewById(R.id.messageHeader);
      //  sendingText = findViewById(R.id.sendingText);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        keyEventManager = new KeyEventManager(vibrator);
        switchJoyCon = getRightNintendoSwitchJoyCon();
        System.out.println(switchJoyCon.getName());

        viewPager.setAdapter(new viewpagerAdapter(getSupportFragmentManager()));


    }



    /**
     * method that listens for keyevents
     * @param keyCode keycode of the button pressed
     * @param event the event from the input device
     * @return boolean
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //todo home button. could maybe be used to clear the sent text
        //keyCode == KeyEvent.KEYCODE_BUTTON_MODE)

        System.out.println(event.getKeyCode());
        keyEventManager.handleEvent(keyCode, this);


        return super.onKeyDown(keyCode, event);
    }


    /**
     * has to overwrite this method since the B button is considered back key by android
     * (right now it is just an empty implementation, so that the back key does not do anything)
     */
    @Override
    public void onBackPressed() {}


    //todo --> maybe instead of returning null if a nintendo joy con is not detected it
    // should throw an exeption ?
    /**
     * Helper method that checks if the input device is a game controller. If so it Checks if the
     * game controller is a Right Nintendo Joy Con.
     *
     * @return A Right Nintendo Joy Con or null
     */
    public InputDevice getRightNintendoSwitchJoyCon() {

        //gets all the ID's of the connected input devices
        int[] deviceIds = InputDevice.getDeviceIds();

        //initializes each input device from its ID
        for (int deviceId : deviceIds) {
            InputDevice dev = InputDevice.getDevice(deviceId);
            int sources = dev.getSources();


            // Verify that the device has gamepad buttons, control sticks, or both.
            if (((sources & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD)
                    || ((sources & InputDevice.SOURCE_JOYSTICK)
                    == InputDevice.SOURCE_JOYSTICK)) {

                // Checks if the gamecontroller is a right Nintendo Switch Joy Con
                if (dev.getName().equalsIgnoreCase("Nintendo Switch Right Joy-Con")) {
                    return dev;
                }
            }
        }
        return null;
    }

    private class viewpagerAdapter extends FragmentPagerAdapter{


        public viewpagerAdapter(@NonNull FragmentManager fm) {super(fm);}


        @NonNull
        @Override
        public Fragment getItem(int position) {

            Fragment f = null;

            switch (position){
                case 0: f = sendfragment;
                    break;
                case 1: f = recivefragment;
                    break;
                default:{
                    f = sendfragment;
                    System.out.println("Pageviewer error");
                }
                    break;

            }
            return f;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void changeSendingFragmentTextView(String string){
        ((TextView)sendfragment.getView().findViewById(R.id.sendingText)).setText(string);
    }

    public void appendToSendingFragmentTextView(String string){
        ((TextView)sendfragment.getView().findViewById(R.id.sendingText)).append(string);
    }

    public String getSendingFragmenttext(){
        return ((TextView)sendfragment.getView().findViewById(R.id.sendingText)).getText().toString();
    }




}

