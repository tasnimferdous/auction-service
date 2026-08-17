package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import org.springframework.data.domain.Page;

public interface PublicAuctionService {
    AuctionDetailsResponse getAuctionDetails(Long auctionId);

    Page<AuctionResponse> getActiveAuctions(
            int page,
            int size,
            String sortBy,
            String direction);
}
