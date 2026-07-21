package com.tasnim.auctionservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionCreateRequest {
    @NotNull(message = "listingId is required")
    private Long listingId;
    @NotBlank(message = "sellerId is required")
    private String sellerId;
    @NotNull(message = "startingPrice is required")
    private BigDecimal startingPrice;
    @NotNull(message = "reservePrice is required")
    private BigDecimal reservePrice;
    @NotNull(message = "buyNowPrice is required")
    private BigDecimal buyNowPrice;
    @NotNull(message = "startedAt is required")
    private Instant startedAt;
    @NotNull(message = "endedAt is required")
    private Instant endedAt;
}
