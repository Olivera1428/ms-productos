package com.codigo.ms_productos.service.impl;

import com.codigo.ms_productos.dto.ProductoRequestDto;
import com.codigo.ms_productos.dto.ProductoResponseDto;
import com.codigo.ms_productos.entity.Producto;
import com.codigo.ms_productos.exception.ProductoNotFoundException;
import com.codigo.ms_productos.repository.ProductoRepository;
import com.codigo.ms_productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponseDto crearProducto(ProductoRequestDto request) {

        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .estado(request.getEstado())
                .fechaCreacion(LocalDateTime.now())
                .build();

        Producto productoGuardado = productoRepository.save(producto);

        return convertirDto(productoGuardado);
    }

    @Override
    public List<ProductoResponseDto> listarProductos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    @Override
    public ProductoResponseDto obtenerProductoPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(
                        "No existe producto con ID " + id
                ));

        return convertirDto(producto);
    }

    @Override
    public ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto request) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(
                        "No existe producto con ID " + id
                ));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setEstado(request.getEstado());

        Producto actualizado = productoRepository.save(producto);

        return convertirDto(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(
                        "No existe producto con ID " + id
                ));

        productoRepository.delete(producto);
    }

    private ProductoResponseDto convertirDto(Producto producto) {

        return ProductoResponseDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .estado(producto.getEstado())
                .fechaCreacion(producto.getFechaCreacion())
                .build();
    }
}