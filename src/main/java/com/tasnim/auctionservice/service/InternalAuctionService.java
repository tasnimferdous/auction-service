package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.request.AuctionCreateRequest;
import com.tasnim.auctionservice.dto.request.BidUpdateRequest;

public interface InternalAuctionService {
    void createAuction(AuctionCreateRequest request);

    void completeAuction(Long auctionId);

    void cancelAuction(Long auctionId);

    void processBidUpdate(Long auctionId, BidUpdateRequest request);
}
