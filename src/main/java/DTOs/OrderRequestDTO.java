package DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDTO {
    private Long id;
    private String orderId;
    private String userId;
    private long amount;
    private String status;
}
