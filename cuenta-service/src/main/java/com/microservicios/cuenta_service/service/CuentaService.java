package com.microservicios.cuenta_service.service;

import com.microservicios.cuenta_service.entity.Cuenta;
import com.microservicios.cuenta_service.entity.Movimiento;
import com.microservicios.cuenta_service.repository.CuentaRepository;
import com.microservicios.cuenta_service.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            cuenta.setEstado(cuentaActualizada.isEstado());
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
            .filter(mov -> mov.getCuentaId().equals(cuentaId))
            .toList();
}

        // ==============================
    // Reporte de Estado de Cuenta
    // ==============================

public List<Cuenta> generarReporte(Long clienteId, String fechaInicio, String fechaFin) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
    LocalDate fin = LocalDate.parse(fechaFin, formatter);

    List<Cuenta> cuentasCliente = cuentaRepository.findByClienteId(clienteId);

    // Para cada cuenta, buscar movimientos dentro del rango
    for (Cuenta cuenta : cuentasCliente) {
        List<Movimiento> movimientosFiltrados = movimientoRepository.findAll()
                .stream()
                .filter(m -> m.getCuentaId().equals(cuenta.getNumeroCuenta()))
                .filter(m -> {
                    
                    LocalDate fechaMov = m.getFecha();

                    return (fechaMov.isEqual(inicio) || fechaMov.isAfter(inicio)) &&
                           (fechaMov.isEqual(fin) || fechaMov.isBefore(fin));
                })
                .toList();

        // Si quieres devolver los movimientos junto con la cuenta, crea un DTO
        // Por ejemplo: CuentaReporteDTO { Cuenta cuenta; List<Movimiento> movimientos; }
        
    }

    return cuentasCliente;
}
}
