package com.sandy.monedas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandy.monedas.entities.Moneda;
import com.sandy.monedas.entities.MonedaId;

import java.util.Optional;
@Repository

public interface MonedaRepository extends JpaRepository<Moneda, MonedaId>{
	boolean existsByClaveMoneda(String claveMoneda);
	//Optional<Moneda> findByClaveMoneda(String claveMoneda);
	//void deleteByClaveMoneda(String claveMoneda);
	boolean existsByNumCiaAndClaveMoneda(Long numCia, String claveMoneda);
	
	Optional<Moneda> findByNumCiaAndClaveMoneda(Long numCia, String claveMoneda);
	void deleteByNumCiaAndClaveMoneda(Long numCia, String claveMoneda);
	
	
	
	
 }


