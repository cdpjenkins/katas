package com.cdpjenkins.katas.simplemarsrover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarsRoverTest {

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

record Position(int x, int y) {
    public Position wrap(int gridSize) {
        return new Position(x % gridSize, y % gridSize);
    }
}

record MarsRover(Position position, Direction direction) {
    String asString() {
        return String.format("%d:%d:%c", position().x(), position().y(), direction().toChar());
    }
}

enum Direction {
    NORTH {
        @Override
        Direction turnLeft() {
            return WEST;
        }

        @Override
        Direction turnRight() {
            return EAST;
        }

        @Override
        Position move(Position position) {
            return new Position(position.x(), position.y() - 1);
        }
    },
    EAST {
        @Override
        Direction turnLeft() {
            return NORTH;
        }

        @Override
        Direction turnRight() {
            return SOUTH;
        }

        @Override
        Position move(Position position) {
            return new Position(position.x() + 1, position.y());
        }
    },
    SOUTH {
        @Override
        Direction turnLeft() {
            return EAST;
        }

        @Override
        Direction turnRight() {
            return WEST;
        }

        @Override
        Position move(Position position) {
            return new Position(position.x(), position.y() + 1);
        }
    },
    WEST {
        @Override
        Direction turnLeft() {
            return SOUTH;
        }

        @Override
        Direction turnRight() {
            return NORTH;
        }

        @Override
        Position move(Position position) {
            return new Position(position.x() - 1, position.y());
        }
    };

    abstract Direction turnLeft();
    abstract Direction turnRight();
    abstract Position move(Position position);

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
    MOVE {
        @Override
        MarsRover execute(MarsRover marsRover) {
            Position wrappedPosition = marsRover.direction().move(marsRover.position()).wrap(9);
            return new MarsRover(wrappedPosition, marsRover.direction());
        }
    },
    TURN_LEFT {
        @Override
        MarsRover execute(MarsRover marsRover) {
            return new MarsRover(marsRover.position(), marsRover.direction().turnLeft());
        }
    },
    TURN_RIGHT {
        @Override
        MarsRover execute(MarsRover marsRover) {
            return new MarsRover(marsRover.position(), marsRover.direction().turnRight());
        }
    };

    abstract MarsRover execute(MarsRover marsRover);

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
        return commands.chars()
                .mapToObj(c -> Command.fromChar((char) c))
                .toList()
                .stream()
                .reduce(new MarsRover(new Position(0, 0), Direction.NORTH),
                        (rover, command) -> command.execute(rover),
                        (rover1, rover2) -> rover2
                ).asString();
    }

}
