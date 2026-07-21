package com.tasnim.auctionservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidUpdateRequest {
    @NotBlank(message = "bidderId is required")
    private String bidderId;
    @NotNull(message = "amount is required")
    private BigDecimal amount;
}