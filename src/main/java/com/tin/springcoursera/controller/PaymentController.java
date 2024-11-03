package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.response.CompletedOrder;
import com.tin.springcoursera.dto.response.PaymentOrder;
import com.tin.springcoursera.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/paypal")
@CrossOrigin(origins = "http://localhost:4200, https://angular-coursera.nicebeach-963cb903.northcentralus.azurecontainerapps.io")
public class PaymentController {
    @Autowired
    private PaypalService paypalService;

    @PostMapping(value = "/create-order")
    @PreAuthorize("not @auth.isMemberOfCourse(#courseId)")
    public PaymentOrder createPayment(
            @RequestParam("courseId") int courseId) throws IOException {
        return paypalService.createPayment(courseId);
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@RequestParam("token") String token) throws IOException {
        return paypalService.completePayment(token);
    }
}
