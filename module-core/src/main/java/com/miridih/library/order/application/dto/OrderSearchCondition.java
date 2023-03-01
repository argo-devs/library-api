package com.miridih.library.order.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@ToString
public class OrderSearchCondition {
    private Long accountId;
    private Pageable pageable;
}
