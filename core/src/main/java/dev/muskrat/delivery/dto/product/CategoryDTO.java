package dev.muskrat.delivery.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    private Long id;
    private String title;
}