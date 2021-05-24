package com.mycompany.myapp;

// using apple audio services to play short system sounds
// (30 secs or shorter)
import org.robovm.apple.audiotoolbox.AudioFile;
import org.robovm.apple.audiotoolbox.AudioServices;

import org.robovm.apple.corefoundation.OSStatusException;
import org.robovm.apple.foundation.NSBundle;
import org.robovm.apple.foundation.NSURL;

public class ButtonSounds extends AudioServices {
    private static int blipSound() throws OSStatusException {
        int qSound = 1000;          // notification sound of an iPhone
        try {
            final NSBundle bundle = NSBundle.getMainBundle();

            // NSURL is an object representing the location of a resource that bridges to URL;
            // use NSURL when you need reference semantics or other Foundation-specific behavior.
            final NSURL soundURL = new NSURL((bundle.findResourcePath("blip",  "wav")));
            qSound = createSystemSoundID(soundURL);     // createSystemSoundID(NSURL)
            System.out.println("Initialised aSoundPtr: " + qSound);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You have forgotten to add your sound file");
        }
        System.out.println("sound ID now is " + qSound);
        return qSound;
    }

    private static int swooshSound() throws OSStatusException {
        int sSound = 1000;

        // Unable to identify media file location
        final NSBundle bundle = NSBundle.getMainBundle();
        final NSURL swooshURL = new NSURL((bundle.findResourcePath("swoosh",  "mp3")));
        // creates a system sound object from a URL - swoosh is in the resources folder
        sSound = createSystemSoundID(swooshURL);
        return sSound; // aSound is the associated object with the swoosh audio file
    }

    private static int clickSound() throws OSStatusException {
        int cSound = 1000;

        // Unable to identify media file location
        final NSBundle bundle = NSBundle.getMainBundle();
        final NSURL clickURL = new NSURL((bundle.findResourcePath("click",  "mp3")));
        // creates a system sound object from a URL - click is also in the resources folder
        cSound = createSystemSoundID(clickURL);
        return cSound;
    }

    public static void playSwoosh() throws OSStatusException {
        playSystemSound(swooshSound());
    }

    public static int prepSwoosh() throws OSStatusException {
        return swooshSound();
    }

    public static void playClick() throws OSStatusException {
        playSystemSound(clickSound());
    }

    public static int prepClick() throws OSStatusException {
        return clickSound();
    }

    public static void playBlip() throws OSStatusException {
        playSystemSound(blipSound());
    }

    public static int prepBlip() throws OSStatusException {
        return blipSound();
    }

}
