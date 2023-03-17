package com.miridih.library.order.ui;

import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import com.miridih.library.order.application.BackofficeOrderService;
import com.miridih.library.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeOrderController {

    private final BackofficeOrderService backofficeOrderService;

    @GetMapping("/order")
    public BackofficeResponse<OrderListResponse> getOrders(Pageable pageable) {
        log.info("ORDR:SRCH:RQST: 주문 조회 요청. [pageable={}]", pageable);

        try {
            Page<Order> orders = backofficeOrderService.getOrders(pageable);

            return BackofficeResponse.of(OrderListResponse.from(orders));
        } catch (Exception e) {
            log.error("ORDR:SRCH:FAIL: 주문 조회중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PutMapping("/order")
    public BackofficeResponse<OrderResponse> updateOrder(@RequestBody OrderUpdateRequest request) {
        log.info("ORDR:UPDT:RQST: 주문 변경 요청. [request={}]", request);

        try {
            Order order = backofficeOrderService.updateOrder(request.toOrderUpdateInput());

            return BackofficeResponse.of(OrderResponse.from(order));
        } catch (Exception e) {
            log.error("ORDR:UPDT:FAIL: 주문 변경중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @DeleteMapping("/order/{orderId}")
    public BackofficeResponse<OrderResponse> deleteOrder(@PathVariable Long orderId) {
        log.info("ORDR:DEL_:RQST: 주문 변경 요청. [order={}]", orderId);

        try {
            backofficeOrderService.deleteOrder(orderId);

            return BackofficeResponse.of(OrderResponse.of(orderId));
        } catch (Exception e) {
            log.error("ORDR:DEL_:FAIL: 주문 삭제중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
