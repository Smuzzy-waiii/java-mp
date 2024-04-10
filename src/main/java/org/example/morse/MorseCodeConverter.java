package org.example.morse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MorseCodeConverter {
    @Autowired
    MorseCodeService morseCodeService;
    private static final Map<String, String> morseCodeToEnglishMap = new HashMap<>();
    private static final Map<String, String> englishToMorseMap = new HashMap<>();

    static {
        // Populate Morse code to English map
        morseCodeToEnglishMap.put(".-", "A");
        morseCodeToEnglishMap.put("-...", "B");
        morseCodeToEnglishMap.put("-.-.", "C");
        morseCodeToEnglishMap.put("-..", "D");
        morseCodeToEnglishMap.put(".", "E");
        morseCodeToEnglishMap.put("..-.", "F");
        morseCodeToEnglishMap.put("--.", "G");
        morseCodeToEnglishMap.put("....", "H");
        morseCodeToEnglishMap.put("..", "I");
        morseCodeToEnglishMap.put(".---", "J");
        morseCodeToEnglishMap.put("-.-", "K");
        morseCodeToEnglishMap.put(".-..", "L");
        morseCodeToEnglishMap.put("--", "M");
        morseCodeToEnglishMap.put("-.", "N");
        morseCodeToEnglishMap.put("---", "O");
        morseCodeToEnglishMap.put(".--.", "P");
        morseCodeToEnglishMap.put("--.-", "Q");
        morseCodeToEnglishMap.put(".-.", "R");
        morseCodeToEnglishMap.put("...", "S");
        morseCodeToEnglishMap.put("-", "T");
        morseCodeToEnglishMap.put("..-", "U");
        morseCodeToEnglishMap.put("...-", "V");
        morseCodeToEnglishMap.put(".--", "W");
        morseCodeToEnglishMap.put("-..-", "X");
        morseCodeToEnglishMap.put("-.--", "Y");
        morseCodeToEnglishMap.put("--..", "Z");
        morseCodeToEnglishMap.put("-----", "0");
        morseCodeToEnglishMap.put(".----", "1");
        morseCodeToEnglishMap.put("..---", "2");
        morseCodeToEnglishMap.put("...--", "3");
        morseCodeToEnglishMap.put("....-", "4");
        morseCodeToEnglishMap.put(".....", "5");
        morseCodeToEnglishMap.put("-....", "6");
        morseCodeToEnglishMap.put("--...", "7");
        morseCodeToEnglishMap.put("---..", "8");
        morseCodeToEnglishMap.put("----.", "9");
        morseCodeToEnglishMap.put("/", " ");
        // Special characters
        morseCodeToEnglishMap.put(".-.-.-", ".");
        morseCodeToEnglishMap.put("--..--", ",");
        morseCodeToEnglishMap.put("..--..", "?");
        morseCodeToEnglishMap.put(".----.", "'");
        morseCodeToEnglishMap.put("-.-.--", "!");
        morseCodeToEnglishMap.put("-..-.", "/");
        morseCodeToEnglishMap.put("-.--.", "(");
        morseCodeToEnglishMap.put("-.--.-", ")");
        morseCodeToEnglishMap.put(".-...", "&");
        morseCodeToEnglishMap.put("---...", ":");
        morseCodeToEnglishMap.put("-.-.-.", ";");
        morseCodeToEnglishMap.put("-...-", "=");
        morseCodeToEnglishMap.put(".-.-.", "+");
        morseCodeToEnglishMap.put("-....-", "-");
        morseCodeToEnglishMap.put("..--.-", "_");
        morseCodeToEnglishMap.put(".-..-.", "\"");
        morseCodeToEnglishMap.put("...-..-", "$");
        morseCodeToEnglishMap.put(".--.-.", "@");

        // Populate English to Morse code map
        for (Map.Entry<String, String> entry : morseCodeToEnglishMap.entrySet()) {
            englishToMorseMap.put(entry.getValue(), entry.getKey());
        }
    }

    public String morseToEnglish(String morseCode) {
        // Implement Interpreter Pattern here
        // Convert Morse code to English using morseCodeToEnglishMap
        StringBuilder englishText = new StringBuilder();
        String[] words = morseCode.split("\\s{3}");
        for (String word : words) {
            String[] letters = word.split("\\s+");
            for (String letter : letters) {
                if (morseCodeToEnglishMap.containsKey(letter)) {
                    englishText.append(morseCodeToEnglishMap.get(letter));
                }
                else{
                    return "undefined";
                }
            }
            englishText.append(" ");
        }
        String result = englishText.toString().trim();
        morseCodeService.saveConversation(true, result, morseCode);
        return result;
    }

    public String englishToMorse(String text) {
        // Implement Builder Pattern here
        // Convert English to Morse code using englishToMorseMap
        StringBuilder morseCode = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            String letter = String.valueOf(c);
            if (englishToMorseMap.containsKey(letter)) {
                morseCode.append(englishToMorseMap.get(letter)).append(" ");
            }
            else{
                return "undefined "+letter;
            }
        }
        String result = morseCode.toString().trim();
        morseCodeService.saveConversation(false, text, result);
        return result;
    }
}
