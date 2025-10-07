package com.cdpjenkins.katas.smartfridge;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SmartFridgeTest {

    @Test
    void fridge_is_initially_empty() {
        SmartFridge smartFridge = new SmartFridge();

        String contents = smartFridge.formatContents();

        assertThat(contents, isOutput(""));
    }

    @Test
    void fridge_door_is_initially_closed() {
        SmartFridge smartFridge = new SmartFridge();

        assertThat(smartFridge.isDoorOpen(), is(false));
    }

    private static Matcher<String> isOutput(String expectedOutput) {
        return is(expectedOutput.stripIndent().trim());
    }
}

class SmartFridge {
    public String formatContents() {
        return "";
    }

    public boolean isDoorOpen() {
        return false;
    }
}
