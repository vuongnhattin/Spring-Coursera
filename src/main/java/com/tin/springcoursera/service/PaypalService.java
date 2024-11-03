package com.tin.springcoursera.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.dto.response.CompletedOrder;
import com.tin.springcoursera.dto.response.PaymentOrder;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.entity.Orders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaypalService {

    @Value("${frontend.url}")
    private String frontendUrl;

    private final PayPalHttpClient payPalHttpClient;

    private final CourseService courseService;
    private final OrderService orderService;
    private final UserService userService;
    private final MemberService memberService;

    public PaymentOrder createPayment(int courseId) throws IOException {
        Course course = courseService.getCourseById(courseId);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown().currencyCode("USD").value(course.getPrice().toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl(String.format("%s/payment/review", frontendUrl))
                .cancelUrl(String.format("%s/payment/cancel", frontendUrl));
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

//        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            orderService.createOrder(userService.getCurrentUser().getId(), courseId, order.id());

            return new PaymentOrder("success",  order.id(), redirectUrl);
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            return new PaymentOrder("Error");
//        }
    }

    @Transactional
    public CompletedOrder completePayment(String token) throws IOException {
//        try {
            OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                Orders order = orderService.getOrderByToken(token);
                memberService.joinCourse(new JoinCourseRequest(order.getCourseId()), userService.getCurrentUser().getUsername());

                return new CompletedOrder("success", token);
            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
        return new CompletedOrder("error");
    }
}
