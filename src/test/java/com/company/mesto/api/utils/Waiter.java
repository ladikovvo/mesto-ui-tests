package com.company.mesto.api.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class Waiter {
    private Waiter() {}

    public static void untilTrue(
            Duration timeout,
            Duration pollInterval,
            BooleanSupplier condition,
            Supplier<String> failMessage
    ) {
        Instant deadline = Instant.now().plus(timeout);

        while (Instant.now().isBefore(deadline)) {
            if (condition.getAsBoolean()) return;

            try {
                Thread.sleep(pollInterval.toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError("Interrupted while waiting condition", e);
            }
        }

        throw new AssertionError(failMessage.get());
    }
}
