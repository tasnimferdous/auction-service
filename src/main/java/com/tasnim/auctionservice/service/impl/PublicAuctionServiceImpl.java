package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.entity.Auction;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.mapper.AuctionMapper;
import com.tasnim.auctionservice.repository.AuctionRepository;
import com.tasnim.auctionservice.service.PublicAuctionService;
import com.tasnim.commonlibrary.exceptions.BadRequestException;
import com.tasnim.commonlibrary.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tasnim.auctionservice.utils.Constants.ALLOWED_SORT_FIELDS;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PublicAuctionServiceImpl implements PublicAuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public PublicAuctionServiceImpl(
            AuctionRepository auctionRepository,
            AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    @Override
    public AuctionDetailsResponse getAuctionDetails(Long auctionId) {
        log.info("Fetching auction details. auctionId={}", auctionId);

        return auctionMapper
                .toAuctionDetailsResponse(getAuction(auctionId));
    }

    @Override
    public Page<AuctionResponse> getActiveAuctions(int page, int size, String sortBy, String direction) {
        log.info("Fetching active auctions. page={}, size={}, sortBy={}, direction={}",
                page, size, sortBy, direction);

        validateSorting(sortBy, direction);
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return auctionRepository
                .findByStatus(AuctionStatus.ACTIVE, pageable)
                .map(auctionMapper::toAuctionResponse);
    }

    private Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Auction not found with id: " + auctionId));
    }

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy);
        sort = "desc".equalsIgnoreCase(direction)
                ? sort.descending()
                : sort.ascending();

        return PageRequest.of(page, size, sort);
    }

    private void validateSorting(String sortBy, String direction) {
        if (!"asc".equalsIgnoreCase(direction)
                && !"desc".equalsIgnoreCase(direction)) {
            throw new BadRequestException(
                    "Invalid sort direction");
        }

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new BadRequestException(
                    "Invalid sort field: " + sortBy);
        }
    }
}
