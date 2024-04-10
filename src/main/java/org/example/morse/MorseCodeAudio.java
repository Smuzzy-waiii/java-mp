package org.example.morse;

import javax.sound.sampled.*;

public class MorseCodeAudio implements Audio {

    // Define the duration of a dot (in milliseconds)
    private static final int DOT_DURATION = 200;

    // Define the frequency of the tone (in hertz)
    private static final int TONE_FREQUENCY = 1000;

    @Override
    public void play(String text) {
        try {
            // Obtain the audio format
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);

            // Open the audio line
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();

            // Iterate through each character in the Morse code text
            for (char c : text.toCharArray()) {
                switch (c) {
                    case '.':
                        playTone(line, DOT_DURATION);
                        break;
                    case '-':
                        playTone(line, 3 * DOT_DURATION); // Duration of a dash is 3 times that of a dot
                        break;
                    case ' ':
                        // Add a brief pause between characters
                        Thread.sleep(DOT_DURATION);
                        break;
                }
                // Add a brief pause between dots and dashes within a character
                Thread.sleep(DOT_DURATION);
            }

            // Wait for the audio to finish playing
            Thread.sleep(1000);

            // Close the audio line
            line.stop();
            line.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to play a tone of the specified duration
    private void playTone(SourceDataLine line, int duration) throws InterruptedException {
        byte[] buffer = new byte[duration * 44100 / 1000];

        for (int i = 0; i < buffer.length; i++) {
            double angle = i / (44100.0 / TONE_FREQUENCY) * 2.0 * Math.PI;
            buffer[i] = (byte) (Math.sin(angle) * 127.0);
        }

        line.write(buffer, 0, buffer.length);
    }
}
