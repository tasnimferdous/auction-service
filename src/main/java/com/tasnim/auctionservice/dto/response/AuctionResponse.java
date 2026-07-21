package com.tasnim.auctionservice.dto.response;

import com.tasnim.auctionservice.enums.AuctionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionResponse {
    private Long id;
    private Long listingId;
    private BigDecimal currentPrice;
    private Long bidCount;
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;
    private Instant startedAt;
    private Instant endedAt;
}