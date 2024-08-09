package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.ProductoDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('EMPL')")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('EMPL')")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

//    @GetMapping("/detailname/{nombre}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('EMPL')")
//    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
//        if(!productoService.existsByNombre(nombre))
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        Producto producto = productoService.getByNombre(nombre).get();
//        return new ResponseEntity(producto, HttpStatus.OK);
//    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPL')")
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
//        if(StringUtils.isBlank(productoDto.getNombre()))
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
//            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
//        if(productoService.existsByNombre(productoDto.getNombre()))
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
//        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
//        productoService.save(producto);
//        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
//    }
@PreAuthorize("hasRole('ADMIN') or hasRole('EMPL')")
@PostMapping("/create")
public ResponseEntity<?> create(@RequestBody ProductoDto productoDto) {
    // Validación de campos
    if (StringUtils.isBlank(productoDto.getNombre())) {
        return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isBlank(productoDto.getCategoria())) {
        return new ResponseEntity<>(new Mensaje("La Categoria es obligatorio"), HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isBlank(productoDto.getDescripcion())) {
        return new ResponseEntity<>(new Mensaje("Una Descripcion obligatoria"), HttpStatus.BAD_REQUEST);
    }
    if (productoDto.getTalla() == null || productoDto.getTalla() < 0) {
        return new ResponseEntity<>(new Mensaje("Numero de talla debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
    }
    if (productoDto.getEstado() == null) {
        return new ResponseEntity<>(new Mensaje("El estado es obligatorio"), HttpStatus.BAD_REQUEST);
    }
    if (productoDto.getPrecio() == null || productoDto.getPrecio() < 0) {
        return new ResponseEntity<>(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
    }
    Producto producto = new Producto(
            productoDto.getNombre(),
            productoDto.getCategoria(),
            productoDto.getDescripcion(),
            productoDto.getTalla(),
            productoDto.getEstado(),
            productoDto.getPrecio());
    productoService.save(producto);
    return new ResponseEntity<>(new Mensaje("Producto creado"), HttpStatus.OK);
}


    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPL')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el producto con ese ID"), HttpStatus.NOT_FOUND);;
        if (StringUtils.isBlank(productoDto.getNombre())) {
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(productoDto.getCategoria())) {
            return new ResponseEntity<>(new Mensaje("La Categoria es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(productoDto.getDescripcion())) {
            return new ResponseEntity<>(new Mensaje("Una Descripcion obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getTalla() == null || productoDto.getTalla() < 0) {
            return new ResponseEntity<>(new Mensaje("Numero de talla debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getEstado() == null) {
            return new ResponseEntity<>(new Mensaje("El estado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getPrecio() == null || productoDto.getPrecio() < 0) {
            return new ResponseEntity<>(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setCategoria(productoDto.getCategoria());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setTalla(productoDto.getTalla());
        producto.setEstado(productoDto.getEstado());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
