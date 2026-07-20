package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.request.AuctionCreateRequest;
import com.tasnim.auctionservice.dto.request.BidUpdateRequest;
import com.tasnim.auctionservice.dto.response.AuctionResponse;

public interface InternalAuctionService {
    AuctionResponse createAuction(AuctionCreateRequest request);

    void completeAuction(Long auctionId);

    void cancelAuction(Long auctionId);

    void processBidUpdate(Long auctionId, BidUpdateRequest request);
}
