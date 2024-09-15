package com.example.blog_backend.Controller;

import com.example.blog_backend.Service.PaymentService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/subscriptions")
public class PaymentController {
        private PaymentService paymentService;
@Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/charge")
        public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
            return this.paymentService.chargeNewCard(token, amount);
        }
    }