package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.service.AdminAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AdminAuctionServiceImpl implements AdminAuctionService {
    @Override
    public void cancelAuction(Long auctionId) {

    }

    @Override
    public Page<AuctionResponse> getAuctions(AuctionStatus status, int page, int size) {
        return null;
    }
}
