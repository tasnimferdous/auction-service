
package com.tasnim.auctionservice.service;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import org.springframework.data.domain.Page;

public interface AdminAuctionService {

    Page<AuctionResponse> getAuctionsByStatus(
            AuctionStatus status,
            int page,
            int size,
            String sortBy,
            String direction);
}
