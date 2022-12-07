package com.example.kardex.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.kardex.entidad.Kardex;

@Repository
public interface KardexJPA extends CrudRepository<Kardex, Integer>{
	List<Kardex> findByIdProducto(Integer idProducto);
}
