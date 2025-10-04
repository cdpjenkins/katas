package com.cdpjenkins.katas.simplemarsrover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMarsRoverTest {

    @Test
    void rover_starts_at_position_0_0_facing_north() {
        assertThat(MarsRoverExecutor.execute(""), is("0:0:N"));
    }

    @Test
    void y_coord_is_incremented_by_1_when_we_move_north() {
        assertThat(MarsRoverExecutor.execute("M"), is("0:1:N"));
    }

    @Test
    void y_coord_is_incremented_by_2_when_we_move_north_twice() {
        assertThat(MarsRoverExecutor.execute("MM"), is("0:2:N"));
    }

    @Test
    void rover_wraps_around_when_we_reach_the_top_of_the_grid() {
        assertThat(MarsRoverExecutor.execute("MMMMMMMMMM"), is("0:0:N"));
    }

    @Test
    void rover_faces_west_after_turning_left() {
        assertThat(MarsRoverExecutor.execute("L"), is("0:0:W"));
    }

    @Test
    void rover_faces_south_after_turning_left_twice() {
        assertThat(MarsRoverExecutor.execute("LL"), is("0:0:S"));
    }

    @Test
    void rover_faces_east_after_turning_left_three_times() {
        assertThat(MarsRoverExecutor.execute("LLL"), is("0:0:E"));
    }

    @Test
    void rover_faces_north_again_after_turning_left_fourth_times() {
        assertThat(MarsRoverExecutor.execute("LLLL"), is("0:0:N"));
    }

    @Test
    void rover_faces_east_after_turning_right() {
        assertThat(MarsRoverExecutor.execute("R"), is("0:0:E"));
    }

    @Test
    void rover_faces_south_after_turning_right_twice() {
        assertThat(MarsRoverExecutor.execute("RR"), is("0:0:S"));
    }

    @Test
    void rover_faces_west_after_turning_right_three_times() {
        assertThat(MarsRoverExecutor.execute("RRR"), is("0:0:W"));
    }

    @Test
    void rover_faces_north_again_after_turning_right_fourth_times() {
        assertThat(MarsRoverExecutor.execute("RRRR"), is("0:0:N"));
    }

    @Test
    void x_coord_is_incremented_by_1_when_we_move_east() {
        assertThat(MarsRoverExecutor.execute("RM"), is("1:0:E"));
    }

    @Test
    void rover_wraps_around_when_we_reach_the_right_edge_of_the_grid() {
        assertThat(MarsRoverExecutor.execute("RMMMMMMMMMM"), is("0:0:E"));
    }

    @Test
    void rover_can_move_south_and_wraps_around_when_we_reach_the_bottom_edge_of_the_grid() {
        assertThat(MarsRoverExecutor.execute("LLM"), is("0:9:S"));
    }

    @Test
    void rover_can_move_west_and_wraps_around_when_we_reach_the_left_edge_of_the_grid() {
        assertThat(MarsRoverExecutor.execute("LM"), is("9:0:W"));
    }

    @ParameterizedTest
    @CsvSource({
            "MMRMMLM, 2:3:N",
            "MMMMMMMMMM, 0:0:N",
            "RMMLM, 2:1:N"
    })
    void rover_moves_correctly_when_given_more_complex_commands(String commands, String finalState) {
        assertThat(MarsRoverExecutor.execute(commands), is(finalState));
    }
}

record MarsRover(int x, int y) { }

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    char toChar() {
        return switch (this) {
            case NORTH -> 'N';
            case EAST -> 'E';
            case SOUTH -> 'S';
            case WEST -> 'W';
        };
    }
}

enum Command {
    MOVE,
    TURN_LEFT,
    TURN_RIGHT;

    static Command fromChar(char c) {
        return switch (c) {
            case 'M' -> MOVE;
            case 'L' -> TURN_LEFT;
            case 'R' -> TURN_RIGHT;
            default -> throw new IllegalArgumentException("Invalid command: " + c);
        };
    }
}

class MarsRoverExecutor {
    public static String execute(String commands) {
        Direction direction = Direction.NORTH;

        MarsRover marsRover = new MarsRover(0, 0);

        for (char c : commands.toCharArray()) {

            Command command = Command.fromChar(c);

            switch (command) {
                case MOVE -> marsRover = move(marsRover, direction);
                case TURN_LEFT -> direction = switch (direction) {
                    case Direction.NORTH -> Direction.WEST;
                    case Direction.WEST  -> Direction.SOUTH;
                    case Direction.SOUTH -> Direction.EAST;
                    case Direction.EAST  -> Direction.NORTH;
                };
                case TURN_RIGHT -> direction = switch (direction) {
                    case Direction.NORTH -> Direction.EAST;
                    case Direction.EAST -> Direction.SOUTH;
                    case Direction.SOUTH -> Direction.WEST;
                    case Direction.WEST -> Direction.NORTH;
                };
            }
        }

        return String.format("%d:%d:%c", marsRover.x(), marsRover.y(), direction.toChar());
    }

    private static MarsRover move(MarsRover marsRover, Direction direction) {
        int x = marsRover.x();
        int y = marsRover.y();

        switch (direction) {
            case NORTH -> {
                y++;
                if (y >= 10) {
                    y = 0;
                }
            }
            case EAST -> {
                x++;
                if (x >= 10) {
                    x = 0;
                }
            }
            case SOUTH -> {
                y--;
                if (y < 0) {
                    y = 9;
                }
            }
            case WEST -> {
                x--;
                if (x < 0) {
                    x = 9;
                }
            }
        }

        marsRover = new MarsRover(x, y);
        return marsRover;
    }
}
