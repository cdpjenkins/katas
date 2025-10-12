package com.cdpjenkins.katas.smartfridge;

import java.time.LocalDate;

public record Item(String name, LocalDate expiryDate, Condition condition, int hoursDegraded) {

    public Item(String name, LocalDate expiryDate, Condition condition) {
        this(name, expiryDate, condition, 0);
    }

    public Item degrade() {
        int hoursTodegrade = switch (condition) {
            case Condition.SEALED -> 1;
            case Condition.OPENED -> 5;
        };

        return new Item(
                this.name,
                this.expiryDate,
                this.condition,
                this.hoursDegraded + hoursTodegrade
        );
    }
}

