package service;

import model.OrderDetails;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService implements Payment{
    @Override
    public String generatePaymentLink(OrderDetails orderDetails) {
        return "";
    }
}
