package com.tasnim.auctionservice.entity;

import com.tasnim.auctionservice.enums.AuctionEndReason;
import com.tasnim.auctionservice.enums.AuctionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_auction_listing_id", columnList = "listingId"),
                @Index(name = "idx_auction_seller_id", columnList = "sellerId"),
                @Index(name = "idx_auction_status", columnList = "status"),
                @Index(name = "idx_auction_winner_id", columnList = "winnerId"),
                @Index(name = "idx_auction_seller_status", columnList = "sellerId,status"),
                @Index(name = "idx_auction_status_ended_at", columnList = "status,endedAt")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
