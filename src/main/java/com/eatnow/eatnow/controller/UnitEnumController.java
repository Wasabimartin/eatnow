package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.enums.UnitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/enums")
public class UnitEnumController {

    @GetMapping("/units")
    public List<UnitEnum> getUnitEnums() {
        // Direkt die Enum-Werte zurückgeben
        return Arrays.asList(UnitEnum.values());
    }


}