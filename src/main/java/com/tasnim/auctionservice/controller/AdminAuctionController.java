package com.tasnim.auctionservice.controller;

import com.tasnim.auctionservice.dto.response.AuctionResponse;
import com.tasnim.auctionservice.enums.AuctionStatus;
import com.tasnim.auctionservice.service.AdminAuctionService;
import com.tasnim.commonlibrary.model.CommonResponse;
import com.tasnim.commonlibrary.utils.ResponseUtil;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Validated
@RestController
@RequestMapping("/api/v1/admin/auctions")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAuctionController {

    private final AdminAuctionService adminAuctionService;

    public AdminAuctionController(AdminAuctionService adminAuctionService) {
        this.adminAuctionService = adminAuctionService;
    }

    @GetMapping("/status/{status}")
    public CommonResponse<Page<AuctionResponse>> getAuctionsByStatus(
            @PathVariable AuctionStatus status,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<AuctionResponse> response =
                adminAuctionService.getAuctionsByStatus(status, page, size, sortBy, direction);

        return ResponseUtil.success(response, "Auctions retrieved successfully");
    }
}
