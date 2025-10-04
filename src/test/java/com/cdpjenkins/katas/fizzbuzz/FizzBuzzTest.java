package com.cdpjenkins.katas.fizzbuzz;

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

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12, 18, 21})
    void converts_number_that_is_divisible_by_3_but_not_by_5_to_Fizz(int input) {
        assertThat(fizzBuzz.convert(input), is("Fizz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 25, 35, 40})
    void converts_number_that_is_divisible_by_5_but_not_by_3_to_Buzz(int input) {
        assertThat(fizzBuzz.convert(input), is("Buzz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45, 60, 75, 90})
    void converts_number_that_is_divisible_by_both_3_and_5_to_FizzBuzz(int input) {
        assertThat(fizzBuzz.convert(input), is("FizzBuzz"));
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
        mustBePositive(number);

        if (isDivisibleBy(number, 3) && isDivisibleBy(number, 5)) {
            return "FizzBuzz";
        } else if (isDivisibleBy(number, 3)) {
            return "Fizz";
        } else if (isDivisibleBy(number, 5)) {
            return "Buzz";
        } else {
            return Integer.toString(number);
        }
    }

    private static void mustBePositive(int number) {
        if (!isPositive(number)) {
            throw new IllegalArgumentException("Input must be positive");
        }
    }

    private static boolean isPositive(int number) {
        return number >= 1;
    }

    private static boolean isDivisibleBy(int number, int factor) {
        return number % factor == 0;
    }
}
