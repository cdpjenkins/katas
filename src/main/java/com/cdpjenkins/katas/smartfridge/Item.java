package com.cdpjenkins.katas.smartfridge;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Item(String name, LocalDateTime expiryDate, Condition condition) {

    public Item(String name, LocalDate expiryDate, Condition condition) {
        this(name, expiryDate.atStartOfDay(), condition);
    }

    public Item degrade() {
        int hoursTodegrade = switch (condition) {
            case Condition.SEALED -> 1;
            case Condition.OPENED -> 5;
        };

        return new Item(
                this.name,
                this.expiryDate.minusHours(hoursTodegrade),
                this.condition
        );
    }
}

