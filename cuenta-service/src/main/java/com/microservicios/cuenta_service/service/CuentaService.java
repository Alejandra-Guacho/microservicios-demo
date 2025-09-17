package com.microservicios.cuenta_service.service;

import com.microservicios.cuenta_service.entity.Cuenta;
import com.microservicios.cuenta_service.entity.Movimiento;
import com.microservicios.cuenta_service.repository.CuentaRepository;
import com.microservicios.cuenta_service.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    // Crear una nueva cuenta
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    // Listar todas las cuentas
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    // Buscar cuenta por ID
    public Optional<Cuenta> obtenerCuenta(Long id) {
        return cuentaRepository.findById(id);
    }

    // Actualizar cuenta
    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        return cuentaRepository.findById(id).map(cuenta -> {
            cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
            cuenta.setEstado(cuentaActualizada.getEstado());
            return cuentaRepository.save(cuenta);
        }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    // Eliminar cuenta
    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    // Obtener movimientos de una cuenta
    public List<Movimiento> obtenerMovimientosPorCuenta(Long cuentaId) {
        return movimientoRepository.findAll()
                .stream()
                .filter(mov -> mov.getCuenta().getId().equals(cuentaId))
                .toList();
    }
}
