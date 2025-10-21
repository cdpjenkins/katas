package com.cdpjenkins.katas.vibebuzz;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VibeBuzzTest {
    @Test
    void should_return_string_representation_of_positive_integer() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(1), is("1"));
    }

    @Test
    void should_return_vibe_for_integer_divisible_by_2() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(2), is("Vibe"));
    }

    @Test
    void should_return_buzz_for_integer_divisible_by_3() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(3), is("Buzz"));
    }

    @Test
    void should_return_vibebuzz_for_integer_divisible_by_both_2_and_3() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThat(vibeBuzz.vibeBuzz(6), is("VibeBuzz"));
    }

    @Test
    void should_throw_exception_for_zero() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThrows(IllegalArgumentException.class, () -> vibeBuzz.vibeBuzz(0));
    }

    @Test
    void should_throw_exception_for_negative_number() {
        VibeBuzz vibeBuzz = new VibeBuzz();

        assertThrows(IllegalArgumentException.class, () -> vibeBuzz.vibeBuzz(-1));
    }
}
