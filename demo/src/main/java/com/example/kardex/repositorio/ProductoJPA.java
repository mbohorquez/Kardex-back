package com.example.kardex.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.kardex.entidad.Producto;

@Repository
public interface ProductoJPA extends CrudRepository<Producto, Integer>{

}
