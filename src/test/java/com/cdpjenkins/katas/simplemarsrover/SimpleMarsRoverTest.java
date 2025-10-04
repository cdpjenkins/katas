package com.cdpjenkins.katas.simplemarsrover;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMarsRoverTest {

    @Test
    void rover_starts_at_position_0_0_facing_north() {
        assertThat(MarsRover.execute(""), is("0:0:N"));
    }
}

class MarsRover {
    public static String execute(String command) {
        return "0:0:N";
    }
}
