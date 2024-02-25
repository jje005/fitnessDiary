package com.suresofttech.fitness.model;

public enum BodyPart {
    CHEST(Messages.BodyPart_0),
    SHOULDER(Messages.BodyPart_1),
    LAT(Messages.BodyPart_2),
    LEG(Messages.BodyPart_3),
    ABS(Messages.BodyPart_4),
    ARMS(Messages.BodyPart_5);

    private String koreanName;


    
    BodyPart(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static BodyPart fromKoreanName(String koreanName) {
        for (BodyPart bodyPart : BodyPart.values()) {
            if (bodyPart.getKoreanName().equals(koreanName)) {
                return bodyPart;
            }
        }
        return null;
    }
    

}
