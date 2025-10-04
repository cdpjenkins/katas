package com.cdpjenkins.katas.fizzbuzz;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class FizzBuzzTest {
    @Test
    void converts_1_to_1() {
        assertThat(new FizzBuzz().convert(1), Matchers.is("1"));
    }

    @Test
    void converts_2_to_2() {
        assertThat(new FizzBuzz().convert(2), Matchers.is("2"));
    }
}

class FizzBuzz {
    public String convert(int number) {
        return Integer.toString(number);
    }
}
