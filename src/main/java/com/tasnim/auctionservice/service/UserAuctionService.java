package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import org.springframework.data.domain.Page;

public interface UserAuctionService {

    Page<AuctionResponse> getMyAuctions(
            int page,
            int size,
            String sortBy,
            String direction);

    Page<AuctionResponse> getWonAuctions(
            int page,
            int size,
            String sortBy,
            String direction);
}