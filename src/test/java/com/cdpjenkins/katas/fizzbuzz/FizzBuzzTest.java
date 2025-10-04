package com.cdpjenkins.katas.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz = new FizzBuzz();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 7, 8})
    void converts_number_that_is_not_divisible_by_3_or_5_to_string_representation_of_that_number(int input) {
        assertThat(fizzBuzz.convert(input), is(input + ""));
    }

    @Test
    void converts_3_to_Fizz() {
        assertThat(fizzBuzz.convert(3), is("Fizz"));
    }

    @Test
    void converts_5_to_Buzz() {
        assertThat(fizzBuzz.convert(5), is("Buzz"));
    }

    @Test
    void converts_6_to_Fizz() {
        assertThat(fizzBuzz.convert(6), is("Fizz"));
    }

    @Test
    void converts_10_to_Buzz() {
        assertThat(fizzBuzz.convert(10), is("Buzz"));
    }

    @Test
    void converts_15_to_FizzBuzz() {
        assertThat(fizzBuzz.convert(15), is("FizzBuzz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void throws_IllegalArguementException_when_input_is_not_positive(int input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> fizzBuzz.convert(input)
        );
    }
}

class FizzBuzz {
    public String convert(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Input must be positive");
        } else if (isDivisibleBy(number, 3) && isDivisibleBy(number, 5)) {
            return "FizzBuzz";
        } else if (isDivisibleBy(number, 3)) {
            return "Fizz";
        } else if (isDivisibleBy(number, 5)) {
            return "Buzz";
        } else {
            return Integer.toString(number);
        }
    }

    private static boolean isDivisibleBy(int number, int factor) {
        return number % factor == 0;
    }
}
