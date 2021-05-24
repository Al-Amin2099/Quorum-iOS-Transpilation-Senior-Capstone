package com.mycompany.myapp;

import org.robovm.apple.corefoundation.OSStatusException;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.*;

// class that contains the system sounds we use
import com.mycompany.myapp.ButtonSounds;


/**
 *
 * @author alleew
 */

/**
 * @contributors: An Vo, Tony
 */

public class MyViewController extends UIViewController {
    private UIButton button;
    private UIButton buttonStop;
    private UIButton buttonPause;
    private UIButton buttonContinue;
    private UIButton buttonSpeakMessage;
    private UILabel label;
    private int clickCount;
    private UIView view;

    // add a textfield and a label
    public MyViewController() {
        // Get the view of this view controller.
        view = getView();

        // Setup background.
        view.setBackgroundColor(UIColor.black());

        // Setup label.
        label = new UILabel(new CGRect(20, 250, 280, 44));
        label.setFont(UIFont.getSystemFont(24));
        label.setTextAlignment(NSTextAlignment.Center);
        view.addSubview(label);
        buttonDefineQuorumLanguage();
        buttonPause();
        buttonContinue();
        buttonStop();

        UITextField textField = new UITextField(new CGRect(20, 250, 350, 40));
        textField.setBorderStyle(UITextBorderStyle.Line);
        textField.setText("Type here");
        textField.setBackgroundColor(UIColor.lightGray());

        view.addSubview(textField);

        buttonSpeakMessage(textField);
    }

    // add a button speak for the textField
    public void buttonSpeakMessage(UITextField textField){
        buttonSpeakMessage = new UIButton(UIButtonType.RoundedRect);
        buttonSpeakMessage.setFrame(new CGRect(125, 300, 100, 40)); // setting width to 100 fixed it for me
        buttonSpeakMessage.setTitle("Speak", UIControlState.Normal);
        buttonSpeakMessage.getTitleLabel().setFont(UIFont.getSystemFont(22));
        buttonSpeakMessage.addOnTouchUpInsideListener((control, event) -> {
            // speak button plays a swoosh sound effect when its pressed
            try {
                ButtonSounds.playSwoosh();
            } catch (OSStatusException e) {
                e.printStackTrace();
            }
            TextToSpeech.setSpeech(textField.getText());
        });
        view.addSubview(buttonSpeakMessage);
    }

    // add a button to read the text file in the resource director
    public void buttonDefineQuorumLanguage(){
        // Setup button.
        button = new UIButton(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(35, 75, 300, 40));
        button.setTitle("Define Quorum", UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));
        button.addOnTouchUpInsideListener((control, event) -> {
            try {
                ButtonSounds.playBlip();

            } catch (OSStatusException e) {
                e.printStackTrace();
            }
            TextToSpeech.speak();
        });
        view.addSubview(button);
    }

    // add a button to pause the reading process
    public void buttonPause(){
        // Setup button.
        buttonPause = new UIButton(UIButtonType.RoundedRect);
        buttonPause.setFrame(new CGRect(10, 150, 100, 40));
        buttonPause.setTitle("Pause", UIControlState.Normal);
        buttonPause.getTitleLabel().setFont(UIFont.getSystemFont(22));
        buttonPause.addOnTouchUpInsideListener((control, event) -> {
            // pause button plays a click sound when its pressed
            try {
                ButtonSounds.playClick();
            } catch (OSStatusException e) {
                e.printStackTrace();
            }
            TextToSpeech.pause();
        });
        view.addSubview(buttonPause);
    }

    // add a button to continue the reading process
    public void buttonContinue(){
        // Setup button.
        buttonContinue = new UIButton(UIButtonType.RoundedRect);
        buttonContinue.setFrame(new CGRect(125, 150, 150, 40));
        buttonContinue.setTitle("Continue", UIControlState.Normal);
        buttonContinue.getTitleLabel().setFont(UIFont.getSystemFont(22));
        buttonContinue.addOnTouchUpInsideListener((control, event) -> {
            // continue button plays a click sound when its pressed
            try {
                ButtonSounds.playClick();
            } catch (OSStatusException e) {
                e.printStackTrace();
            }
            TextToSpeech.continueSpeaking();
        });
        view.addSubview(buttonContinue);
    }

    // add a button to stop the reading process
    public void buttonStop(){
        // Setup button.
        buttonStop = new UIButton(UIButtonType.RoundedRect);
        buttonStop.setFrame(new CGRect(325, 150, 75, 40));
        buttonStop.setTitle("Stop", UIControlState.Normal);
        buttonStop.getTitleLabel().setFont(UIFont.getSystemFont(22));
        buttonStop.addOnTouchUpInsideListener((control, event) -> {
            // stop button plays a click sound when its pressed
            try {
                ButtonSounds.playClick();
            } catch (OSStatusException e) {
                e.printStackTrace();
            }
            TextToSpeech.stopSpeaking();
        });
        view.addSubview(buttonStop);
    }

}
