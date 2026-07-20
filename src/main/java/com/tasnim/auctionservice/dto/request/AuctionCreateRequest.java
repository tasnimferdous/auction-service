package com.tasnim.auctionservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AuctionCreateRequest {
    @NotNull(message = "listingId is required")
    private Long listingId;
    private String sellerId;
    @NotNull(message = "startingPrice is required")
    private BigDecimal startingPrice;
    @NotNull(message = "reservePrice is required")
    private BigDecimal reservePrice;
    @NotNull(message = "buyNowPrice is required")
    private BigDecimal buyNowPrice;
}
