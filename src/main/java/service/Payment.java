package service;

import com.stripe.exception.StripeException;
import model.OrderDetails;

public interface Payment {
    public String generatePaymentLink(OrderDetails orderDetails) throws StripeException;
}
