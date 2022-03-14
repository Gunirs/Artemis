package com.github.gunirs.artemis.api;

import java.util.Arrays;
import java.util.Optional;

public enum GuiType {
    NONE, SHOP;

    public static Optional<GuiType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(guiType -> guiType.ordinal() == value)
                .findFirst();
    }
}
