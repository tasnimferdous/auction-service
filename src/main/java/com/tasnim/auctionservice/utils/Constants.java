package com.tasnim.auctionservice.utils;

import java.util.List;

public class Constants {
    private Constants() {}

    public static final List<String> ALLOWED_SORT_FIELDS = List.of(
            "createdAt",
            "currentPrice",
            "startedAt",
            "endedAt");
}
