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
    void rover_faces_west_after_turning_left() {
        assertThat(MarsRover.execute("L"), is("0:0:W"));
    }

    @Test
    void rover_faces_south_after_turning_left_twice() {
        assertThat(MarsRover.execute("LL"), is("0:0:S"));
    }

    @Test
    void rover_faces_east_after_turning_left_three_times() {
        assertThat(MarsRover.execute("LLL"), is("0:0:E"));
    }

    @Test
    void rover_faces_north_again_after_turning_left_fourth_times() {
        assertThat(MarsRover.execute("LLLL"), is("0:0:N"));
    }

    @Test
    void rover_faces_east_after_turning_right() {
        assertThat(MarsRover.execute("R"), is("0:0:E"));
    }

    @Test
    void rover_faces_south_after_turning_right_twice() {
        assertThat(MarsRover.execute("RR"), is("0:0:S"));
    }

    @Test
    void rover_faces_west_after_turning_right_three_times() {
        assertThat(MarsRover.execute("RRR"), is("0:0:W"));
    }

    @Test
    void rover_faces_north_again_after_turning_right_fourth_times() {
        assertThat(MarsRover.execute("RRRR"), is("0:0:N"));
    }

    @Test
    void x_coord_is_incremented_by_1_when_we_move_east() {
        assertThat(MarsRover.execute("RM"), is("1:0:E"));
    }
}

class MarsRover {
    public static String execute(String commands) {
        char direction = 'N';
        int x = 0;
        int y = 0;

        for (char command : commands.toCharArray()) {
            if (command == 'M') {

                switch (direction) {
                    case 'N' -> {
                        y++;
                        if (y >= 10) {
                            y = 0;
                        }
                    }
                    case 'E' -> {
                        x++;
                    }
                }
            } else if (command == 'L') {
                direction = switch (direction) {
                    case 'N' -> 'W';
                    case 'W' -> 'S';
                    case 'S' -> 'E';
                    case 'E' -> 'N';
                    default -> direction;
                };
            } else if (command == 'R') {
                direction = switch (direction) {
                    case 'N' -> 'E';
                    case 'E' -> 'S';
                    case 'S' -> 'W';
                    case 'W' -> 'N';
                    default -> direction;
                };
            }
        }

        return String.format("%d:%d:%c", x, y, direction);
    }
}
