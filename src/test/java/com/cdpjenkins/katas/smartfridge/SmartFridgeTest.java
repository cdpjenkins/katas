package com.cdpjenkins.katas.smartfridge;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SmartFridgeTest {

    SmartFridge smartFridge = new SmartFridge();

    private static final LocalDate YESTERDAY = LocalDate.of(2021, 10, 20);
    private static final LocalDate TODAY = LocalDate.of(2021, 10, 21);
    private static final LocalDate TOMORROW = LocalDate.of(2021, 10, 22);

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
        smartFridge.addItem(new Item("Milk", TODAY));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("Milk: 0 days remaining"));
    }

    @ParameterizedTest
    @CsvSource({"Milk", "Cheese"})
    void the_days_remaining_is_reported_for_an_item_that_was_just_placed_in_the_fridge(String itemName) {
        smartFridge.openDoor();
        smartFridge.addItem(new Item(itemName, TOMORROW));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput(itemName + ": 1 day remaining"));
    }

    @ParameterizedTest
    @CsvSource({"Milk", "Cheese"})
    void the_days_remaining_is_reported_for_an_expired_item_that_was_just_placed_in_the_fridge(String itemName) {
        smartFridge.openDoor();
        smartFridge.addItem(new Item(itemName, YESTERDAY));
        smartFridge.closeDoor();

        assertThat(smartFridge.formatContents(TODAY),
                isOutput("EXPIRED: " + itemName));
    }

    private static Matcher<String> isOutput(String expectedOutput) {
        return is(expectedOutput.stripIndent().trim());
    }
}

record Item(String name, LocalDate expiryDate) {}

class SmartFridge {
    private boolean doorOpen = false;

    private final List<Item> items = new ArrayList<>();

    public String formatContents(LocalDate now) {
        return items.stream()
                .map((item) -> daysRemaining(item, now))
                .collect(Collectors.joining("\n"));
    }

    private static String daysRemaining(Item item, LocalDate now) {
        Period periodBetween = Period.between(now, item.expiryDate());

        if (periodBetween.isNegative()) {
            return String.format("EXPIRED: " + item.name());
        }

        int numDays = periodBetween.getDays();
        String dayOrDays = numDays == 1 ? "day" : "days";

        return String.format(item.name() + ": %d %s remaining", numDays, dayOrDays);
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void openDoor() {
        doorOpen = true;
    }

    public void closeDoor() {
        doorOpen = false;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
