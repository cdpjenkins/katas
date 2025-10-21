package com.cdpjenkins.katas.vibebuzz;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VibeBuzzTest {
    @Test
    void shouldReturnStringRepresentationOfPositiveInteger() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(1), is("1"));
    }

    @Test
    void shouldReturnVibeForIntegerDivisibleBy2() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(2), is("Vibe"));
    }

    @Test
    void shouldReturnBuzzForIntegerDivisibleBy3() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(3), is("Buzz"));
    }
}
