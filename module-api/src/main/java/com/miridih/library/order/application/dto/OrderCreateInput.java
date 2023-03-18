package com.miridih.library.order.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCreateInput {
    private String title;
    private int quantity;
    private String link;
    private String note;
}
