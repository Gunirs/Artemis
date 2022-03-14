package com.github.gunirs.artemis.api;

import java.util.Arrays;
import java.util.Optional;

public enum PacketType {
    NONE, PRODUCT_GETTING, GUI_OPEN, PRODUCT_BUY;

    public static Optional<PacketType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(packetType -> packetType.ordinal() == value)
                .findFirst();
    }
}
