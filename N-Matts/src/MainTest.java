import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main_inits(){
        Board board = new Board();

        assertEquals("Init", board.input);
    }

    @Test
    void n_matts_driver_4() {
        NMatt test = new NMatt(4);

        int board[][] = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        test.drive(board);
    }

    @Test
    void n_matts_driver_10() {
        NMatt test = new NMatt(10);

        int board[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        test.drive(board);
    }

    @Test
    void n_matts_driver_simple_example() {
        NMatt test = new NMatt(4);

        int board[][] = {
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        test.drive(board);
    }

    @Test
    void n_matts_driver_impossible_example() {
        NMatt test = new NMatt(4);

        int board[][] = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        test.drive(board);
    }

    @Test
    void n_matts_driver_0_should_exit() {
        NMatt test = new NMatt(0);

    }

    @Test
    void n_matts_driver_8_2() {
        NMatt test = new NMatt(4);

        int board[][] = {
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        test.drive(board);
    }

}