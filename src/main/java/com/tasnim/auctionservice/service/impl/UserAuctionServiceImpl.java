package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.mapper.AuctionMapper;
import com.tasnim.auctionservice.repository.AuctionRepository;
import com.tasnim.auctionservice.service.UserAuctionService;
import com.tasnim.commonlibrary.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tasnim.auctionservice.utils.AuctionUtil.buildPageable;
import static com.tasnim.auctionservice.utils.AuctionUtil.validateSorting;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserAuctionServiceImpl implements UserAuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public UserAuctionServiceImpl(
            AuctionRepository auctionRepository,
            AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    @Override
    public Page<AuctionResponse> getMyAuctions(int page, int size, String sortBy, String direction) {
        String sellerId = SecurityUtil.getCurrentUserId();

        log.info("Fetching auctions for seller. sellerId={}, page={}, size={}, sortBy={}, direction={}",
                sellerId, page, size, sortBy, direction);

        validateSorting(sortBy, direction);
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return auctionRepository
                .findBySellerId(sellerId, pageable)
                .map(auctionMapper::toAuctionResponse);
    }

    @Override
    public Page<AuctionResponse> getWonAuctions(int page, int size, String sortBy, String direction) {
        String winnerId = SecurityUtil.getCurrentUserId();

        log.info("Fetching won auctions for user. winnerId={}, page={}, size={}, sortBy={}, direction={}",
                winnerId, page, size, sortBy, direction);

        validateSorting(sortBy, direction);
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return auctionRepository
                .findByWinnerIdAndStatus(winnerId, AuctionStatus.ENDED, pageable)
                .map(auctionMapper::toAuctionResponse);
    }
}
