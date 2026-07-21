package com.tasnim.auctionservice.dto.response;

import com.tasnim.auctionservice.enums.AuctionEndReason;
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
public class AuctionDetailsResponse {
    private Long id;
    private Long listingId;
    private String sellerId;
    private BigDecimal startingPrice;
    private BigDecimal reservePrice;
    private BigDecimal buyNowPrice;
    private BigDecimal currentPrice;
    private String highestBidderId;
    private Long bidCount;
    private String winnerId;
    private BigDecimal winningBidAmount;
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;
    @Enumerated(EnumType.STRING)
    private AuctionEndReason endReason;
    private Instant startedAt;
    private Instant endedAt;
}