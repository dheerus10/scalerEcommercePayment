package service;

import com.stripe.exception.StripeException;
import model.OrderDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService{
    public PaymentGatewaySelector paymentGatewaySelector;

    public PaymentService(PaymentGatewaySelector paymentGatewaySelector){
          this.paymentGatewaySelector = paymentGatewaySelector;
    }

    public String initiatePayment(Long id, String orderId, String userId, long amount) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(id);
        orderDetails.setAmount(amount);
        orderDetails.setStatus("INITIATED");
        orderDetails.setOrderId(orderId);
        orderDetails.setUserId(userId);
        try {
            return paymentGatewaySelector.getPaymentService().generatePaymentLink(orderDetails);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
