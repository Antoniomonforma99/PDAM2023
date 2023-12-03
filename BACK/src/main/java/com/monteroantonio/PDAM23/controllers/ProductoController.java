package com.monteroantonio.PDAM23.controllers;

import com.monteroantonio.PDAM23.model.DTOs.Page.PageDTO;
import com.monteroantonio.PDAM23.model.DTOs.producto.ProductoResponseDTO;
import com.monteroantonio.PDAM23.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    public final ProductoService service;


    @GetMapping("/")
    PageDTO<ProductoResponseDTO> getAall(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        
    }
}
