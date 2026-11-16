package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.mapper.AuctionMapper;
import com.tasnim.auctionservice.repository.AuctionRepository;
import com.tasnim.auctionservice.service.AdminAuctionService;
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
public class AdminAuctionServiceImpl implements AdminAuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public AdminAuctionServiceImpl(
            AuctionRepository auctionRepository,
            AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    @Override
    public Page<AuctionResponse> getAuctionsByStatus(
            AuctionStatus status,
            int page,
            int size,
            String sortBy,
            String direction) {

        log.info("Fetching auctions. status={}, page={}, size={}, sortBy={}, direction={}",
                status, page, size, sortBy, direction);

        validateSorting(sortBy, direction);

        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return auctionRepository
                .findByStatus(status, pageable)
                .map(auctionMapper::toAuctionResponse);
    }
}
