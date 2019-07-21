package dev.muskrat.delivery.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageDTO {

    private List<OrderDTO> orders;
    private Integer currentPage;
    private Integer lastPage;
}
