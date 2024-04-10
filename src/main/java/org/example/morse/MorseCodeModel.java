package org.example.morse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MorseCodeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long convoId;
    private boolean MorEng;
    private String english;
    private String morsecode;

    public MorseCodeModel(Long convoId, boolean morEng, String english, String morsecode) {
        this.convoId = convoId;
        MorEng = morEng;
        this.english = english;
        this.morsecode = morsecode;
    }

    public MorseCodeModel() {

    }

    public Long getConvoId() {
        return convoId;
    }

    public void setConvoId(Long convoId) {
        this.convoId = convoId;
    }

    public boolean isMorEng() {
        return MorEng;
    }

    public void setMorEng(boolean morEng) {
        MorEng = morEng;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMorsecode() {
        return morsecode;
    }

    public void setMorsecode(String morsecode) {
        this.morsecode = morsecode;
    }
}
