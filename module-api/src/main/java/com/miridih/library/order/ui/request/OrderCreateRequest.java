package com.miridih.library.order.ui.request;

import com.miridih.library.order.application.dto.OrderCreateInput;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderCreateRequest {
    private String title;
    private int quantity;
    private String link;
    private String note;

    public OrderCreateInput toOrderCreateInput() {
        return OrderCreateInput
                .builder()
                .title(title)
                .quantity(quantity)
                .link(link)
                .note(note)
                .build();
    }
}
