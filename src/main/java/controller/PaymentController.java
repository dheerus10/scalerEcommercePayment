package controller;

import DTOs.OrderRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.PaymentService;

@RestController
public class PaymentController {
    public PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiatePayment")
    public String initiatePayment(@RequestBody OrderRequestDTO orderRequestDTO) {
        System.out.println("payment initiated..");
       return  paymentService.initiatePayment(orderRequestDTO.getId(),
                orderRequestDTO.getOrderId(),
                orderRequestDTO.getUserId(),
                orderRequestDTO.getAmount());

    }
}
