package dev.muskrat.delivery.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDeleteResponseDTO {

    private Long id;
}