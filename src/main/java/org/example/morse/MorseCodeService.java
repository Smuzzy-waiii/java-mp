package org.example.morse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MorseCodeService {
    private final MorseCodeRepository morseCodeRepository;

    @Autowired
    public MorseCodeService(MorseCodeRepository morseCodeRepository) {
        this.morseCodeRepository = morseCodeRepository;
    }

    public void saveConversation(boolean morEng, String english, String morsecode) {
        MorseCodeModel conversation = new MorseCodeModel();
        conversation.setMorEng(morEng);
        conversation.setEnglish(english);
        conversation.setMorsecode(morsecode);
        morseCodeRepository.save(conversation);
    }

    public List<MorseCodeModel> getConversations() {
        return morseCodeRepository.findAll();
    }
}
