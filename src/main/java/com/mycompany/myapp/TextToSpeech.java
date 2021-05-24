package com.mycompany.myapp;

import org.robovm.apple.avfoundation.AVSpeechBoundary;
import org.robovm.apple.avfoundation.AVSpeechSynthesisVoice;
import org.robovm.apple.avfoundation.AVSpeechSynthesizer;
import org.robovm.apple.avfoundation.AVSpeechUtterance;
import org.robovm.apple.foundation.NSBundle;
import org.robovm.apple.foundation.NSString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


/**
 *
 * @author alleew
 */

/**
 * @contributors: An Vo, Tony
 */

/**
 * @classDescription: Class TextToSpeech reads a text file from the folder resources despite of how big that text file
 *                    may be. It also reads an input text message that an user enters. In other words, whatever the user
 *                    enters in the text box, once activated by the button named Speak, the class will speak word-by-word
 *                    of that message
 * constructors:
 *          TextToSpeech()
 *
 * Static member:
 *      private static AVSpeechSynthesizer speechSynthesizer, speechSynthesizer handles the speaking function
 *      private static AVSpeechUtterance utterance, utterance contains the string that is used for speech
 *
 * Functions:
 *      public static void speak()
 *      public static void setSpeech(String data)
 *      public static void pause()
 *      public static void stopSpeaking()
 *      public static void continueSpeaking()
 */
public class TextToSpeech {

    public TextToSpeech(){

    }
    private static AVSpeechSynthesizer speechSynthesizer = new AVSpeechSynthesizer();
    private static AVSpeechUtterance utterance;
    private static AVSpeechSynthesizer speechSynthesizerMessage = new AVSpeechSynthesizer();

    /**
     * function speak() locates the text file in the resource directory,
     * then it reads and stores the text file into a string, so that string can be
     * inputted into AVSpeechUtterance utterance. function speak() then proceeds
     * to set voice, then enqueue the string data into speechSynthesizer
     * parameters: none
     * @return: none
     */
    public static void speak()
    {
        String data = "";

        // NSBundle is a representation of the code and resources stored in a bundle directory on disk
        final NSBundle bundle = NSBundle.getMainBundle();
        String inputFile = bundle.findResourcePath("quorumLanguage",  "txt");
        try {
            File inFile = new File(inputFile);
            Scanner inFileScanner = new Scanner(inFile);
            while (inFileScanner.hasNextLine()) {
                data += inFileScanner.nextLine();
            }
            inFileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        utterance = new AVSpeechUtterance(data);
        utterance.setVoice(new AVSpeechSynthesisVoice("en-US"));
        speechSynthesizer.enqueueSpeakUtterance(utterance);
    }


    /**
     * function setSpeech() re-constructs utterance with a new string, then deallocates and re-allocates
     * speechSynthesizer in order to clear the queue of audio and get re-written with the new utterance
     * parameters: string data
     * @return: none
     */
    public static void setSpeech(String data)
    {
        utterance = new AVSpeechUtterance(data);
//        speechSynthesizer.enqueueSpeakUtterance(utterance);
        speechSynthesizer.enqueueSpeakUtterance(utterance);
    }

    /**
     * function pause() pauses the speech process of speechSynthesizer
     * parameters: none
     * @return: none
     */
    public static void pause(){
        try {
            speechSynthesizer.pauseSpeaking(AVSpeechBoundary.Immediate);
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    /**
     * function pause() stops the speech process of speechSynthesizer
     * parameters: none
     * @return: none
     */
    public static void stopSpeaking(){
        try {
            speechSynthesizer.stopSpeaking(AVSpeechBoundary.Immediate);
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    /**
     * function pause() continues the speech process of speechSynthesizer after the pause
     * parameters: none
     * @return: none
     */
    public static void continueSpeaking(){
        try {
            speechSynthesizer.continueSpeaking();
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}
