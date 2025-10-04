package com.cdpjenkins.katas.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void converts_1_to_1() {
        assertThat(fizzBuzz.convert(1), is("1"));
    }

    @Test
    void converts_2_to_2() {
        assertThat(fizzBuzz.convert(2), is("2"));
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

    @Test
    void throws_IllegalArguementException_when_input_is_not_positive() {
        assertThrows(
                IllegalArgumentException.class,
                () -> fizzBuzz.convert(0)
        );
    }
}

class FizzBuzz {
    public String convert(int number) {
        if (number == 0) {
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
