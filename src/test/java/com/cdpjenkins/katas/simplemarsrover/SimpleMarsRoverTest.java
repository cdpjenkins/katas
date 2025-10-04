package com.cdpjenkins.katas.simplemarsrover;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMarsRoverTest {

    @Test
    void rover_starts_at_position_0_0_facing_north() {
        assertThat(MarsRover.execute(""), is("0:0:N"));
    }

    @Test
    void y_coord_is_incremented_by_1_when_we_move_north() {
        assertThat(MarsRover.execute("M"), is("0:1:N"));
    }
}

class MarsRover {
    public static String execute(String command) {
        int y = 0;

        if (!command.isEmpty()) {
            y++;
        }

        return String.format("0:%d:N", y);
    }
}
