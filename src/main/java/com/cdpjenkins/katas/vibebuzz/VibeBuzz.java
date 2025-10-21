package com.cdpjenkins.katas.vibebuzz;

public class VibeBuzz {
    public String vibeBuzz(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        if (number % 2 == 0 && number % 3 == 0) {
            return "VibeBuzz";
        }
        if (number % 2 == 0) {
            return "Vibe";
        }
        if (number % 3 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }
}
