package org.urlshortner.service;

import java.security.SecureRandom;
import java.util.List;

public class URLGenerator {

    private static final String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static final SecureRandom random = new SecureRandom();
    private static final int NO_OF_TRIES = 5;
    public static String createShortCode(int length){
        StringBuilder shortCode = new StringBuilder(length);
        for(int i=0; i<length; i++){
            shortCode.append(random.nextInt(characters.length()));
        }
        return shortCode.toString();
    }

    public static String getRandomShortCode(int length, List<String> activeShortCodes) throws Exception {
        for(int i=0; i<NO_OF_TRIES; i++){
            String shortCode = createShortCode(length);
            if(!activeShortCodes.contains(shortCode)) return shortCode;
        }
        System.out.println("Failed to Generate Code. Retry after some time.");
        return null;
    }
}
