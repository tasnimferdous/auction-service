package com.tasnim.auctionservice.controller;

import com.tasnim.auctionservice.dto.request.AuctionCreateRequest;
import com.tasnim.auctionservice.service.InternalAuctionService;
import com.tasnim.commonlibrary.model.CommonResponse;
import com.tasnim.commonlibrary.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/auctions")
public class InternalAuctionController {
    private final InternalAuctionService internalAuctionService;

    public InternalAuctionController(InternalAuctionService internalAuctionService) {
        this.internalAuctionService = internalAuctionService;
    }

    @PostMapping
    public CommonResponse<Void> createAuction(@Valid @RequestBody AuctionCreateRequest request) {
        internalAuctionService.createAuction(request);
        return ResponseUtil.success("Auction created successfully");
    }

    @PostMapping("/{auctionId}/complete")
    public CommonResponse<Void> completeAuction(@PathVariable Long auctionId) {
        internalAuctionService.completeAuction(auctionId);
        return ResponseUtil.success("Auction completed successfully");
    }
}