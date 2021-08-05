package com.example.morsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * https://developer.android.com/guide/input */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //todo skal ikke være her
    TextView sendingText;

    private ViewPager viewPager;
    private InputDevice switchJoyCon;
    private KeyEventManager keyEventManager;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private SendingMessageFragment sendfragment;
    private RecieveMessageFragment recievefragment;
    private String sendingTexview = "";
    private SharedPreferences prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener changeListener;

    // todo --> apparently i could not find a way to make the controller it self vibrate, so
    //  i will just make the phone vibrate
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);


       // recievefragment = (RecieveMessageFragment) getSupportFragmentManager().findFragmentById(R.id.RecievemessageFragment);
       // sendfragment = (SendingMessageFragment) getSupportFragmentManager().findFragmentById(R.id.sendingMessageFragment);



        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        keyEventManager = new KeyEventManager(vibrator, prefs);
        switchJoyCon = getRightNintendoSwitchJoyCon();

        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHost);
        navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navmenu);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("recieveMessage")){
                    keyEventManager.getUserWord(prefs.getString("recieveMessage", ""));
                }
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(changeListener);



    }

    @Override
    protected void onDestroy() {
        prefs.edit().putString("sendingMessage", "");
        super.onDestroy();
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

        keyEventManager.handleEvent(keyCode);
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




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.navItemSend && !(navController.getCurrentDestination().getId() == 2131231090)){

            navController.navigate(R.id.sendingMessageFragment);
            System.out.println(navController.getCurrentDestination().getId()); // 2131231090
        }else if(item.getItemId() == R.id.navItemRecieve && !(navController.getCurrentDestination().getId() == 2131230727)){

            navController.navigate(R.id.RecievemessageFragment);
            System.out.println(navController.getCurrentDestination().getId()); //2131230727
        }

        return false;
    }

}

