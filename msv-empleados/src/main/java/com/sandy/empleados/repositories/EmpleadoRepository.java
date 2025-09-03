package com.sandy.empleados.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandy.empleados.entities.Empleado;
import com.sandy.empleados.entities.EmpleadoId;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoId> {
    boolean existsByNumEmp(Integer numEmp);
	boolean existsByNumCiaAndNumEmp(Long numCia, Integer numEmp);
	Optional<Empleado> findByNumCiaAndNumEmp(Long numCia, Integer numEmp);
	void deleteByNumCiaAndNumEmp(Long numCia, Integer numEmp);
	List<Empleado> findByNumCiaAndClaveMoneda(Long numCia, String claveMoneda);
	
	List<Empleado> findByNumCia(Long numCia);
	List<Empleado> findByNumCiaAndNumEmp(Integer numCia, Integer numEmp);
    List<Empleado> findByNumCiaAndClaveMoneda(Integer numCia, String claveMoneda);	
    Optional<Empleado> findByNumEmp(Integer numEmp);

}