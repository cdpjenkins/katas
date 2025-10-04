package com.cdpjenkins.katas.palindromeradar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromeRadarTest {

    PalindromeRadar palindromeRadar = new PalindromeRadar();

    @Test
    void simple_palindrome_with_only_lower_case_letters_is_recognised() {
        assertThat(palindromeRadar.isPalindrome("anna"), is(true));
    }

    @Test
    void non_palindrome_is_recognised_as_not_palindrome() {
        assertThat(palindromeRadar.isPalindrome("notpalindrome"), is(false));
    }

    @ParameterizedTest
    @CsvSource({
            "rad ar",
            "anna!",
            "race car"
    })
    void ignore_non_alphanumeric_chars(String input) {
        assertThat(palindromeRadar.isPalindrome(input), is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "race car1",
            "Hello, World1"
    })
    void non_plindrome_is_recognised_as_such_even_after_non_alphanumeric_characters_are_ignored(String input) {
        assertThat(palindromeRadar.isPalindrome(input), is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Race car",
            "A man, a plan, a canal, Panama!"
    })
    void differences_in_case_are_ignored(String input) {
        assertThat(palindromeRadar.isPalindrome(input), is(true));
    }
}

class PalindromeRadar {
    public boolean isPalindrome(String input) {
        String normalisedInput = normalise(input);
        return normalisedInput.equals(reverse(normalisedInput));
    }

    private static String reverse(String normalisedInput) {
        return new StringBuilder(normalisedInput).reverse().toString();
    }

    private static String normalise(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
