package dev.muskrat.delivery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerDTO {

    private Long id;
    private String name;

}
