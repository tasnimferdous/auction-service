package com.tasnim.auctionservice.controller;

import com.tasnim.auctionservice.dto.response.AuctionDetailsResponse;
import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.service.PublicAuctionService;
import com.tasnim.commonlibrary.model.CommonResponse;
import com.tasnim.commonlibrary.utils.ResponseUtil;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/public/auctions")
public class PublicAuctionController {
    private final PublicAuctionService publicAuctionService;

    public PublicAuctionController(PublicAuctionService publicAuctionService) {
        this.publicAuctionService = publicAuctionService;
    }

    @GetMapping("/{auctionId}")
    public CommonResponse<AuctionDetailsResponse> getAuctionDetails(@PathVariable Long auctionId) {
        AuctionDetailsResponse response = publicAuctionService.getAuctionDetails(auctionId);

        return ResponseUtil.success(response, "Auction details retrieved successfully");
    }

    @GetMapping("/active")
    public CommonResponse<Page<AuctionResponse>> getActiveAuctions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<AuctionResponse> response =
                publicAuctionService.getActiveAuctions(page, size, sortBy, direction);

        return ResponseUtil.success(response, "Active auctions retrieved successfully");
    }
}