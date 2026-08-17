package com.tasnim.auctionservice.controller;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.service.UserAuctionService;
import com.tasnim.commonlibrary.model.CommonResponse;
import com.tasnim.commonlibrary.utils.ResponseUtil;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/auctions")
public class UserAuctionController {
    private final UserAuctionService userAuctionService;

    public UserAuctionController(UserAuctionService userAuctionService) {
        this.userAuctionService = userAuctionService;
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/owned-by-me")
    public CommonResponse<Page<AuctionResponse>> getMyAuctions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<AuctionResponse> response =
                userAuctionService.getMyAuctions(page, size, sortBy, direction);

        return ResponseUtil.success(response, "Auctions retrieved successfully");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/won-by-me")
    public CommonResponse<Page<AuctionResponse>> getWonAuctions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<AuctionResponse> response =
                userAuctionService.getWonAuctions(page, size, sortBy, direction);

        return ResponseUtil.success(response, "Won auctions retrieved successfully");
    }
}