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

    @Test
    void y_coord_is_incremented_by_2_when_we_move_north_twice() {
        assertThat(MarsRover.execute("MM"), is("0:2:N"));
    }

    @Test
    void rover_wraps_around_when_we_reach_the_top_of_the_grid() {
        assertThat(MarsRover.execute("MMMMMMMMMM"), is("0:0:N"));
    }

    @Test
    void rover_direction_changes_when_it_turns_left() {
        assertThat(MarsRover.execute("L"), is("0:0:W"));
    }

    @Test
    void rover_direction_changes_when_it_turns_right() {
        assertThat(MarsRover.execute("R"), is("0:0:E"));
    }
}

class MarsRover {
    public static String execute(String commands) {
        char direction = 'N';
        int y = 0;

        for (char command : commands.toCharArray()) {
            if (command == 'M') {
                y++;
                if (y >= 10) {
                    y = 0;
                }
            } else if (command == 'L') {
                direction = 'W';
            } else if (command == 'R') {
                direction = 'E';
            }
        }

        return String.format("0:%d:%c", y, direction);
    }
}
