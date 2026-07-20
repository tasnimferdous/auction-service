package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.request.AuctionCreateRequest;
import com.tasnim.auctionservice.dto.request.BidUpdateRequest;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.service.InternalAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class InternalAuctionServiceImpl implements InternalAuctionService {
    @Override
    public AuctionResponse createAuction(AuctionCreateRequest request) {
        return null;
    }

    @Override
    public void completeAuction(Long auctionId) {

    }

    @Override
    public void cancelAuction(Long auctionId) {

    }

    @Override
    public void processBidUpdate(Long auctionId, BidUpdateRequest request) {

    }
}
