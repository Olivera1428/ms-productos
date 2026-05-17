package com.codigo.ms_productos.service;

import com.codigo.ms_productos.dto.ProductoRequestDto;
import com.codigo.ms_productos.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {

    ProductoResponseDto crearProducto(ProductoRequestDto request);

    List<ProductoResponseDto> listarProductos();

    ProductoResponseDto obtenerProductoPorId(Long id);

    ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto request);

    void eliminarProducto(Long id);
}