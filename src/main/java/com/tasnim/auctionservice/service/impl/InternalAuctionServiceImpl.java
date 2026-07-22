package com.tasnim.auctionservice.service.impl;

import com.tasnim.auctionservice.dto.request.AuctionCreateRequest;
import com.tasnim.auctionservice.dto.request.BidUpdateRequest;
import com.tasnim.auctionservice.entity.Auction;
import com.tasnim.auctionservice.enums.AuctionEndReason;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.mapper.AuctionMapper;
import com.tasnim.auctionservice.repository.AuctionRepository;
import com.tasnim.auctionservice.service.InternalAuctionService;
import com.tasnim.commonlibrary.exceptions.BusinessException;
import com.tasnim.commonlibrary.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class InternalAuctionServiceImpl implements InternalAuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public InternalAuctionServiceImpl(
            AuctionRepository auctionRepository,
            AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    @Override
    public void createAuction(AuctionCreateRequest request) {
        log.info("Creating auction for listingId={}", request.getListingId());

        validateAuctionCreation(request);
        Auction auction = buildAuction(request);
        auctionRepository.save(auction);

        log.info("Auction created successfully. auctionId={}, listingId={}",
                auction.getId(), request.getListingId());
    }

    @Override
    public void completeAuction(Long auctionId) {
        log.info("Completing auction. auctionId={}", auctionId);

        Auction auction = getAuction(auctionId);

        if (isTerminalState(auction)) {
            log.info("Auction already completed. auctionId={}", auctionId);
            return;
        }
        if (isSuccessfulAuction(auction)) {
            completeSuccessfulAuction(auction);
        } else {
            completeNoSaleAuction(auction);
        }

        auctionRepository.save(auction);

        log.info("Auction completed successfully. auctionId={}, status={}",
                auctionId, auction.getStatus());
    }

    @Override
    public void cancelAuction(Long auctionId) {

    }

    @Override
    public void processBidUpdate(Long auctionId, BidUpdateRequest request) {
        log.info("Processing bid update. auctionId={}, bidderId={}, amount={}",
                auctionId, request.getBidderId(), request.getAmount());

        Auction auction = getAuction(auctionId);
        validateBidUpdate(auction, request);

        auction.setCurrentPrice(request.getAmount());
        auction.setHighestBidderId(request.getBidderId());
        auction.setBidCount(auction.getBidCount() + 1);
        auctionRepository.save(auction);

        log.info("Bid processed successfully. auctionId={}, bidderId={}, amount={}",
                auctionId, request.getBidderId(), request.getAmount());
    }

    private Auction buildAuction(AuctionCreateRequest request) {
        return Auction.builder()
                .listingId(request.getListingId())
                .sellerId(request.getSellerId())
                .startingPrice(request.getStartingPrice())
                .reservePrice(request.getReservePrice())
                .buyNowPrice(request.getBuyNowPrice())
                .currentPrice(request.getStartingPrice())
                .bidCount(0L)
                .status(AuctionStatus.ACTIVE)
                .startedAt(request.getStartedAt())
                .endedAt(request.getEndedAt())
                .build();
    }

    private void validateAuctionCreation(AuctionCreateRequest request) {
        if (auctionRepository.existsByListingIdAndStatus(
                request.getListingId(), AuctionStatus.ACTIVE)) {
            throw new BusinessException(
                    "Active auction already exists for listing");
        }
    }

    private Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Auction not found with id: " + auctionId
                        ));
    }

    private boolean isTerminalState(Auction auction) {
        return auction.getStatus() == AuctionStatus.ENDED
                || auction.getStatus() == AuctionStatus.NO_SALE
                || auction.getStatus() == AuctionStatus.CANCELLED;
    }

    private boolean isSuccessfulAuction(Auction auction) {
        return auction.getHighestBidderId() != null
                && auction.getCurrentPrice().compareTo(auction.getReservePrice()) >= 0;
    }

    private void completeSuccessfulAuction(Auction auction) {
        auction.setStatus(AuctionStatus.ENDED);
        auction.setWinnerId(auction.getHighestBidderId());
        auction.setWinningBidAmount(auction.getCurrentPrice());
        auction.setEndReason(AuctionEndReason.NORMAL_END);
    }

    private void completeNoSaleAuction(Auction auction) {
        auction.setStatus(AuctionStatus.NO_SALE);
        auction.setWinnerId(null);
        auction.setWinningBidAmount(null);
        auction.setEndReason(AuctionEndReason.NORMAL_END);
    }

    private void validateBidUpdate(Auction auction, BidUpdateRequest request) {
        isActiveAuction(auction);
        validateBidAmount(auction, request.getAmount());
    }

    private void isActiveAuction(Auction auction) {
        if (auction.getStatus() != AuctionStatus.ACTIVE) {
            throw new BusinessException(
                    "Auction is not active");
        }
    }

    private void validateBidAmount(Auction auction, BigDecimal amount) {
        if (amount.compareTo(auction.getCurrentPrice()) <= 0) {
            throw new BusinessException(
                    "Bid amount must be greater than current price");
        }
    }
}
