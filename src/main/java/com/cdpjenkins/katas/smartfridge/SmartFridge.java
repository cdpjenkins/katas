package com.cdpjenkins.katas.smartfridge;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SmartFridge {
    private boolean doorOpen = false;

    private List<Item> items = new ArrayList<>();

    public String formatContents(LocalDate now) {
        return items.stream()
                .map((item) -> daysRemaining(item, now))
                .collect(Collectors.joining("\n"));
    }

    private static String daysRemaining(Item item, LocalDate now) {
        LocalDate expiryDateWithDegradation =
                item.expiryDate()
                        .atStartOfDay()
                        .minusHours(item.hoursDegraded())
                        .toLocalDate();

        Period periodBetween = Period.between(now, expiryDateWithDegradation);

        if (periodBetween.isNegative()) {
            return "EXPIRED: %s".formatted(item.name());
        }

        int numDays = periodBetween.getDays();
        String dayOrDays = numDays == 1 ? "day" : "days";

        return "%s: %d %s remaining".formatted(item.name(), numDays, dayOrDays);
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void openDoor() {
        doorOpen = true;

        List<Item> degradedItems = items.stream().map(Item::degrade).toList();
        this.items = new ArrayList<>(degradedItems);
    }

    public void closeDoor() {
        doorOpen = false;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
