package service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import model.OrderDetails;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements Payment {
    private String stripeKey = "sk_test_51QT3mvFjXY8kx9La8FOSpVguwI1EMroC04lUKe4OwGTeQSKdZJ6mbuTqwHeoBtosCUaYelSXSuAGrqFMBQlEBl6Q00vlUU8vJR";

    @Override
    public String generatePaymentLink(OrderDetails orderDetails) {
        try{
        Stripe.apiKey = this.stripeKey;
        Price price = getPrice(orderDetails);

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://google.com/?trx_id=" + "abc123").build()).build())
                        .build();
        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();
    }catch(StripeException exception){
        throw new RuntimeException(exception.getMessage());
    }
}

    private Price getPrice(OrderDetails orderDetails) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(orderDetails.getAmount())
                            .setProductData(
                                    PriceCreateParams
                                            .ProductData
                                            .builder()
                                            .setName(orderDetails.getOrderId())
                                            .build()
                            )
                            .build();
            Price price = Price.create(params);
            return price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
