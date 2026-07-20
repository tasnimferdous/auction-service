package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.service.PublicAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PublicAuctionServiceImpl implements PublicAuctionService {
    @Override
    public AuctionDetailsResponse getAuction(Long auctionId) {
        return null;
    }

    @Override
    public Page<AuctionResponse> getActiveAuctions(int page, int size) {
        return null;
    }

    @Override
    public Page<AuctionResponse> getWonAuctions(int page, int size) {
        return null;
    }
}
