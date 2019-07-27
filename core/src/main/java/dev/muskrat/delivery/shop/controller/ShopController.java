package dev.muskrat.delivery.shop.controller;

import dev.muskrat.delivery.components.exception.EntityNotFoundException;
import dev.muskrat.delivery.shop.dto.*;
import dev.muskrat.delivery.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/create")
    public ShopCreateResponseDTO create(
        @Valid @RequestBody ShopCreateDTO shopCreateDTO
    ) {
        return shopService.create(shopCreateDTO);
    }

    @PatchMapping("/update")
    public ShopUpdateResponseDTO update(
        @Valid @RequestBody ShopUpdateDTO shopUpdateDTO
    ) {
        return shopService.update(shopUpdateDTO);
    }

    @PatchMapping("/schedule/update")
    public ShopScheduleResponseDTO updateSchedule(
        @Valid @RequestBody ShopScheduleUpdateDTO shopScheduleUpdateDTO
    ) {
        return shopService.updateSchedule(shopScheduleUpdateDTO);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@NotNull @PathVariable Long id) {
        return shopService.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Shop not found")
        );
    }

    @GetMapping("/page")
    public ShopPageDTO page(
        @Valid @RequestBody ShopPageRequestDTO shopPageRequestDTO,
        @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page
    ) {

        return shopService.findAll(shopPageRequestDTO, page);
    }

    @DeleteMapping("/{id}")
    public void delete(@NotNull @PathVariable Long id) {
        shopService.delete(id);
    }

}