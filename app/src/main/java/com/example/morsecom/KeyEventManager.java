package com.example.morsecom;

import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * Class for handeling the keyevents from the Inputdevice
 */
public class KeyEventManager {

    // todo --> apparently i could not find a way to make the controller it self vibrate, so
    //  i will just make the phone vibrate
    Vibrator vibrator;
    StringBuilder morseLetter = new StringBuilder(); // contains the ls combination of a letter
    ArrayList<Character> word = new ArrayList<Character>(); // full word
    ArrayList<String> morseLetterSentence = new ArrayList<>();
    String morseLetterCombination;

    public KeyEventManager(Vibrator v){vibrator = v;}

    public void handleEvent(int keycode, MainActivity activity){

        //apparently it thinks that button A is button B so for now i switched their places
        switch (keycode){

            //Button B
            case KeyEvent.KEYCODE_BUTTON_A: {longMorse();}
                break;

            case KeyEvent.KEYCODE_BUTTON_Y: {shortMorse();}
                break;

            //Button A
            case KeyEvent.KEYCODE_BUTTON_B: {appendWord(activity);}
                break;

            case KeyEvent.KEYCODE_BUTTON_X: {addLetter();}
                break;

            // "+" Button
            case KeyEvent.KEYCODE_BUTTON_START:{appendSpace(activity);}
        }

    }

    private void appendWord(MainActivity activity) {

        StringBuilder activitytext = new StringBuilder();

        //activitytext.append(activity.getSendingFragmenttext());
        System.out.println("Print word");
        System.out.println();
        for (int j = 0; j < word.size(); j++) {
            System.out.print(word.get(j));
            activitytext.append(word.get(j));
        }
        String finaltext = activitytext.toString();
       // activity.changeSendingFragmentTextView(finaltext);
        //activity.sendingText.setText(finaltext);
        System.out.println();
        word = new ArrayList<Character>(); // clears wordprint
    }

    private void shortMorse() {
        System.out.println("Short");
        vibrator.vibrate(300);
        morseLetter.append("S");
    }

    private void longMorse() {
        System.out.println("Long");
        vibrator.vibrate(1000);
        morseLetter.append("L");
    }

    private void addLetter() {
        morseLetterCombination = morseLetter.toString();
        System.out.println("Add letter");
        //PRINT OG TILFØJ BOGSTAV
        switch (morseLetterCombination){
            case "SL" :
                word.add('A');
                System.out.print("A");
                break;
            case "LSSS" :
                word.add('B');
                System.out.print("B");
                break;
            case "LSLS" :
                word.add('C');
                System.out.print("C");
                break;
            case "LSS" :
                word.add('D');
                System.out.print("D");
                break;
            case "S" :
                word.add('E');
                System.out.print("E");
                break;
            case "SSLS" :
                word.add('F');
                System.out.print("F");
                break;
            case "LLS" :
                word.add('G');
                System.out.print("G");
                break;
            case "SSSS" :
                word.add('H');
                System.out.print("H");
                break;
            case "SS" :
                word.add('I');
                System.out.print("I");
                break;
            case "SLLL" :
                word.add('J');
                System.out.print("J");
                break;
            case "LSL" :
                word.add('K');
                System.out.print("K");
                break;
            case "SLSS" :
                word.add('L');
                System.out.print("L");
                break;
            case "LL" :
                word.add('M');
                System.out.print("M");
                break;
            case "LS" :
                word.add('N');
                System.out.print("N");
                break;
            case "LLL" :
                word.add('O');
                System.out.print("O");
                break;
            case "SLLS" :
                word.add('P');
                System.out.print("P");
                break;
            case "LLSL" :
                word.add('Q');
                System.out.print("Q");
                break;
            case "SLS" :
                word.add('R');
                System.out.print("R");
                break;
            case "SSS" :
                word.add('S');
                System.out.print("S");
                break;
            case "L" :
                word.add('T');
                System.out.print("T");
                break;
            case "SSL" :
                word.add('U');
                System.out.print("U");
                break;
            case "SSSL" :
                word.add('V');
                System.out.print("V");
                break;
            case "SLL" :
                word.add('W');
                System.out.print("W");
                break;
            case "LSSL" :
                word.add('X');
                System.out.print("X");
                break;
            case "LSLL" :
                word.add('Y');
                System.out.print("Y");
                break;
            case "LLSS" :
                word.add('Z');
                System.out.print("Z");
                break;

        }

        //TILFØJ ORD OG NULSTIL ORD
        morseLetterSentence.add(morseLetterCombination);
        System.out.println("");
        morseLetter.setLength(0);
    }

    private String charArrayListToString(ArrayList<Character> characterArrayList){

        StringBuilder builder = new StringBuilder();
        for (Character character: characterArrayList) {builder.append(character);}
        return builder.toString();
    }

    public void appendSpace(MainActivity activity){
        System.out.println("Space");
        //activity.appendToSendingFragmentTextView(" ");
      //  activity.sendingText.append(" ");
    }

}


/*


public class Test {

    public static void main(String[] args) throws ControllerUnpluggedException {
        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();




        System.out.println("PRESS B FOR SHORT \nPRESS Y FOR LONG \nPRESS X TO ADD LETTER \nPRESS A TO SEND WORD");
        while (true) {
            while (true) {

                try {
                    if (currController.isButtonJustPressed(ControllerButton.A)) {
                        controllers.doVibration(0, 1, 1, 100);
                        word.append("S");



                    }
                    if (currController.isButtonJustPressed(ControllerButton.X)) {
                        controllers.doVibration(0, 1, 1, 300);
                        word.append("L");


                    }
                    if (currController.isButtonJustPressed(ControllerButton.B)) {

                       //PRINT ORD
                        System.out.println();
                        for (int j = 0; j < wordprint.size(); j++) {
                            System.out.print(wordprint.get(j));

                        }
                        System.out.println();

                        wordprint = new ArrayList<Character>();

                        //ITERER OVER ORD OG GENSKAB I MORSE KODE
                        for (int j = 0; j < fullword.size(); j++) {
                            String stringParse = fullword.get(j);

                            for (int k = 0; k < stringParse.length(); k++) {

                                if(stringParse.charAt(k) == 'S'){
                                    controllers.doVibration(0, 1, 1, 150);


                                } else {
                                    controllers.doVibration(0,1, 1, 400);

                                }


                            }

                        }

                    fullword = new ArrayList<String>();



                    }
                    if (currController.isButtonJustPressed(ControllerButton.Y)) {


                        //TILFØJ ORD OG NULSTIL ORD
                        fullword.add(ord);
                        word.setLength(0);

                        break;

                    }

                } catch (ControllerUnpluggedException e) {
                    break;
                }
            }
        }

    }



}

 */
