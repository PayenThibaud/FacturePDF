package com.facturation.factureservice.utils;

import java.time.Instant;

public class RandomNumero {
    public static String genererRandomNumero() {
        long timestamp = Instant.now().toEpochMilli();
        return String.valueOf(timestamp);
    }
}
