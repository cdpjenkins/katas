package com.cdpjenkins.katas.smartfridge;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SmartFridgeTest {

    SmartFridge smartFridge = new SmartFridge();

    private static final LocalDate YESTERDAY = LocalDate.of(2021, 10, 20);
    private static final LocalDate TODAY = LocalDate.of(2021, 10, 21);
    private static final LocalDate TOMORROW = LocalDate.of(2021, 10, 22);
    private static final LocalDate DAY_AFTER_TOMORROW = LocalDate.of(2021, 10, 23);

    @Test
    void fridge_is_initially_empty() {
        assertThat(smartFridge.formatContents(TODAY), isOutput(""));
    }

    @Test
    void fridge_door_is_initially_closed() {
        assertThat(smartFridge.isDoorOpen(), is(false));
    }

    @Test
    void if_we_open_the_fridge_door_it_is_now_open() {
        smartFridge.openDoor();
        assertThat(smartFridge.isDoorOpen(), is(true));
    }

    @Test
    void if_we_close_an_open_fridge_door_it_is_now_closed() {
        smartFridge.openDoor();
        smartFridge.closeDoor();
        assertThat(smartFridge.isDoorOpen(), is(false));
    }

    @Test
    void item_can_be_placed_in_the_fridge_when_door_is_open() {
        smartFridge.openDoor();
        smartFridge.addItem(new Item("Milk", TODAY, Condition.SEALED));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Milk: 0 days remaining"));
    }

    @ParameterizedTest
    @CsvSource({"Milk", "Cheese"})
    void the_days_remaining_is_reported_for_an_item_that_was_just_placed_in_the_fridge(String itemName) {
        smartFridge.openDoor();
        smartFridge.addItem(new Item(itemName, TOMORROW, Condition.SEALED));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput(itemName + ": 1 day remaining"));
    }

    @ParameterizedTest
    @CsvSource({"Milk", "Cheese"})
    void the_days_remaining_is_reported_for_an_expired_item_that_was_just_placed_in_the_fridge(String itemName) {
        smartFridge.openDoor();
        smartFridge.addItem(new Item(itemName, YESTERDAY, Condition.SEALED));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("EXPIRED: %s".formatted(itemName)));
    }

    @Test
    void opened_item_degrades_by_5_hours_every_time_fridge_is_opened_and_consequently_has_one_less_day_after_fridge_is_opened_once() {
        smartFridge.openDoor();
        smartFridge.addItem(new Item("Milk", TOMORROW, Condition.OPENED));
        smartFridge.closeDoor();

        smartFridge.openDoor();
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Milk: 0 days remaining"));
    }

    @Test
    void opened_item_degrades_by_over_one_day_when_fridge_is_opened_five_times() {
        smartFridge.openDoor();
        smartFridge.addItem(new Item("Milk", DAY_AFTER_TOMORROW, Condition.OPENED));
        smartFridge.closeDoor();

        repeat(5, () -> {
                    smartFridge.openDoor();
                    smartFridge.closeDoor();
                });

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Milk: 0 days remaining"));
    }

    @Test
    void sealed_item_degrades_by_1_hour_every_time_fridge_is_opened_and_consequently_has_one_less_day_after_fridge_is_opened_once() {
        smartFridge.openDoor();
        smartFridge.addItem(new Item("Cheese", TOMORROW, Condition.SEALED));
        smartFridge.closeDoor();

        smartFridge.openDoor();
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Cheese: 0 days remaining"));
    }

    @Test
    void sealed_item_degrades_by_1_day_when_fridge_is_opened_24_times() {
        smartFridge.openDoor();
        smartFridge.addItem(new Item("Cheese", TOMORROW, Condition.SEALED));
        smartFridge.closeDoor();

        repeat(24, () -> {
            smartFridge.openDoor();
            smartFridge.closeDoor();
        });

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Cheese: 0 days remaining"));
    }

    private static Matcher<String> isOutput(String expectedOutput) {
        return is(expectedOutput.stripIndent().trim());
    }

    private static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

}

