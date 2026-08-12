package com.tasnim.auctionservice.repository;

import com.tasnim.auctionservice.entity.Auction;
import com.tasnim.auctionservice.enums.AuctionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Optional<Auction> findByListingId(Long listingId);

    Page<Auction> findByStatus(AuctionStatus status, Pageable pageable);

    Page<Auction> findBySellerId(String sellerId, Pageable pageable);

    Page<Auction> findByWinnerId(String winnerId, AuctionStatus status, Pageable pageable);

    boolean existsByListingIdAndStatus(Long listingId, AuctionStatus status);
}