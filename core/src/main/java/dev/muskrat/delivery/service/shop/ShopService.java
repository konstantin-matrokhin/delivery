
package dev.muskrat.delivery.service.shop;

import dev.muskrat.delivery.dto.shop.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    ShopCreateResponseDTO create(ShopCreateDTO shopDTO);

    ShopUpdateResponseDTO update(ShopUpdateDTO shopDTO);

    ShopScheduleResponseDTO updateSchedule(ShopScheduleUpdateDTO workDayDTO);

    Optional<ShopDTO> findById(Long id);

    Optional<ShopScheduleDTO> findScheduleById(Long id);

    List<ShopDTO> findAll();

    void delete(Long id);

    ShopPageDTO findAll(Pageable page);
}
