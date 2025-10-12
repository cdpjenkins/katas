package com.cdpjenkins.katas.smartfridge;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ItemTest {
    @Test
    void opened_item_degrades_by_5_hours() {
        Item item = new Item(
                "Milk",
                LocalDate.of(2025,Month.JANUARY, 1),
                Condition.OPENED, 0);

        Item degradedItem = item.degrade();

        assertThat(degradedItem.hoursDegraded(), is(5));
    }
}