package com.cdpjenkins.katas.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
}

class FizzBuzz {
    public String convert(int number) {
        if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(number);
        }
    }
}
