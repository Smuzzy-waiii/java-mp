package org.example.morse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class MorseCodeTranslatorController {

    private final MorseCodeConverter morseCodeConverter;
    private final AudioFactory audioFactory;

    @Autowired
    public MorseCodeTranslatorController(MorseCodeConverter morseCodeConverter, AudioFactory audioFactory) {
        this.morseCodeConverter = morseCodeConverter;
        this.audioFactory = audioFactory;
    }

    @Autowired
    MorseCodeService morseCodeService;

    @GetMapping("/")
    public String home() throws IOException {
        // Load home page from static resources
        return loadHomePage();
    }

    @PostMapping("/morseToEnglish")
    public String morseToEnglish(@RequestBody String morseCode) {
        return morseCodeConverter.morseToEnglish(morseCode);
    }

    @PostMapping("/englishToMorse")
    public String englishToMorse(@RequestBody String englishText) {
        return morseCodeConverter.englishToMorse(englishText);
    }

    @GetMapping("/audio/{type}/{text}")
    public void generateAudio(@PathVariable String type, @PathVariable String text) {
        // Use Factory Method Pattern to generate audio
        Audio audio = audioFactory.createAudio(type);
        audio.play(text);
    }

    @GetMapping("/history")
    public List<MorseCodeModel> getHistory() {
        return morseCodeService.getConversations();
    }

    private String loadHomePage() throws IOException {
        Resource resource = new ClassPathResource("static/home-page.html");
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
