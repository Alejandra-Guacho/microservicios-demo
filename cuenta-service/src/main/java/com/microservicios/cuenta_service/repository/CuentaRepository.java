package com.microservicios.cuenta_service.repository;

import com.microservicios.cuenta_service.entity.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

        // Este método permite buscar todas las cuentas de un cliente
    List<Cuenta> findByClienteId(Long clienteId);
}
