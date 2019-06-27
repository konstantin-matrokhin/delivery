package dev.muskrat.delivery.dto.shop;


import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ShopScheduleDTO {

    private Long id;

    private List<LocalTime> open;

    private List<LocalTime> close;
}