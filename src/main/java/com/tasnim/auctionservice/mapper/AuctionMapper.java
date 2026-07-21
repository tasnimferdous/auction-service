package com.tasnim.auctionservice.mapper;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.entity.Auction;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {
    public AuctionResponse toAuctionResponse(Auction auction) {
        return AuctionResponse.builder()
                .id(auction.getId())
                .listingId(auction.getListingId())
                .currentPrice(auction.getCurrentPrice())
                .bidCount(auction.getBidCount())
                .status(auction.getStatus())
                .startedAt(auction.getStartedAt())
                .endedAt(auction.getEndedAt())
                .build();
    }

    public AuctionDetailsResponse toAuctionDetailsResponse(Auction auction) {
        return AuctionDetailsResponse.builder()
                .id(auction.getId())
                .listingId(auction.getListingId())
                .sellerId(auction.getSellerId())
                .startingPrice(auction.getStartingPrice())
                .reservePrice(auction.getReservePrice())
                .buyNowPrice(auction.getBuyNowPrice())
                .currentPrice(auction.getCurrentPrice())
                .highestBidderId(auction.getHighestBidderId())
                .bidCount(auction.getBidCount())
                .winnerId(auction.getWinnerId())
                .winningBidAmount(auction.getWinningBidAmount())
                .status(auction.getStatus())
                .endReason(auction.getEndReason())
                .startedAt(auction.getStartedAt())
                .endedAt(auction.getEndedAt())
                .build();
    }
}
