package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import org.springframework.data.domain.Page;

public interface AdminAuctionService {
    void cancelAuction(Long auctionId);

    Page<AuctionResponse> getAuctions(AuctionStatus status, int page, int size);
}
