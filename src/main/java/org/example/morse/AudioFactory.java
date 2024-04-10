package org.example.morse;

import org.springframework.stereotype.Component;

@Component
public class AudioFactory {
    public Audio createAudio(String type) {
        switch (type) {
            case "morse":
                return new MorseCodeAudio();
            case "english":
                return new EnglishAudio();
            default:
                throw new IllegalArgumentException("Invalid audio type: " + type);
        }
    }
}
