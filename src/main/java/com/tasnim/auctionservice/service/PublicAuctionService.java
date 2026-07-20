package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import org.springframework.data.domain.Page;

public interface PublicAuctionService {
    AuctionDetailsResponse getAuction(Long auctionId);

    Page<AuctionResponse> getActiveAuctions(int page, int size);

    Page<AuctionResponse> getWonAuctions(int page, int size);
}
