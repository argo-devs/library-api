package com.miridih.library.order.ui;

import com.miridih.library.core.ui.response.ApiResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import com.miridih.library.order.application.OrderApiService;
import com.miridih.library.order.domain.Order;
import com.miridih.library.order.exception.OrderException;
import com.miridih.library.order.ui.request.OrderCreateRequest;
import com.miridih.library.order.ui.request.OrderUpdateRequest;
import com.miridih.library.order.ui.response.OrderListResponse;
import com.miridih.library.order.ui.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderApiService orderApiService;

    @GetMapping("/order")
    public ApiResponse<OrderListResponse> getAllOrders(Pageable pageable, Principal principal) {
        log.info("ORDR:SRCH:RQST: 전체 주문 조회 요청. [email={}, request={}]", principal.getName(), pageable);

        try {
            String email = principal.getName();
            Page<Order> orderPage = orderApiService.findAllOrders(email, pageable);

            return ApiResponse.of(OrderListResponse.from(orderPage));
        } catch (OrderException e) {
            log.error("ORDR:SRCH:FAIL:", e);
           return ApiResponse.of(ErrorStatus.O1, "도서 주문에 대해 오류가 발생했습니다.");
        } catch (Exception e) {
            log.error("ORDR:SRCH:ERR_:", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @GetMapping("/order/{orderId}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable Long orderId, Principal principal) {
        log.info("ORDR:FIND:RQST: 주문 조회 요청. [email={}, orderId={}]", principal.getName(), orderId);

        try {
            Order order = orderApiService.findOrder(principal.getName(), orderId);

            return ApiResponse.of(OrderResponse.from(order));
        } catch (OrderException e) {
            log.error("ORDR:FIND:FAIL:", e);
            return ApiResponse.of(ErrorStatus.O1, "도서 주문에 대해 오류가 발생했습니다.");
        } catch (Exception e) {
            log.error("ORDR:FIND:ERR_:", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PostMapping("/order")
    public ApiResponse<OrderResponse> placeOrder(@RequestBody OrderCreateRequest request, Principal principal) {
        log.info("ORDR:RGST:RQST: 주문 생성 요청 [email={}, request={}]", principal.getName(), request);

        try {
            Order order = orderApiService.orderBook(principal.getName(), request.toOrderCreateInput());

            return ApiResponse.of(OrderResponse.from(order));
        } catch (OrderException e) {
            log.error("ORDR:RGST:FAIL:", e);
            return ApiResponse.of(ErrorStatus.O1, "도서 주문에 대해 오류가 발생했습니다.");
        } catch (Exception e) {
            log.error("ORDR:RGST:ERR_:", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PutMapping("/order")
    public ApiResponse<OrderResponse> updateOrder(@RequestBody OrderUpdateRequest request, Principal principal) {
        log.info("ORDR:UPDT:RQST: 주문 변경 요청. [email={}, request={}]", principal.getName(), request);

        try {
            Order order = orderApiService.updateOrder(principal.getName(), request.toOrderUpdateInput());

            return ApiResponse.of(OrderResponse.from(order));
        } catch (OrderException e) {
            log.error("ORDR:UPDT:FAIL:", e);
            return ApiResponse.of(ErrorStatus.O1, "도서 주문에 대해 오류가 발생했습니다.");
        } catch (Exception e) {
            log.error("ORDR:UPDT:ERR_:", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ApiResponse<OrderResponse> deleteOrder(@PathVariable Long orderId, Principal principal) {
        log.info("ORDR:DEL_:RQST: 주문 삭제 요청. [email={}, orderId={}]", principal.getName(), orderId);

        try {
            orderApiService.deleteOrder(principal.getName(), orderId);

            return ApiResponse.of(OrderResponse.of(orderId));
        } catch (OrderException e) {
            log.error("ORDR:DEL_:FAIL:", e);
            return ApiResponse.of(ErrorStatus.O1, "도서 주문에 대해 오류가 발생했습니다.");
        } catch (Exception e) {
            log.error("ORDR:DEL_:ERR_:", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
