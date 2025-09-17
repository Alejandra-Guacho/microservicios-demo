package com.microservicios.cuenta_service.controller;

import com.microservicios.cuenta_service.entity.Cuenta;
import com.microservicios.cuenta_service.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta detalles) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));
        cuenta.setTipoCuenta(detalles.getTipoCuenta());
        cuenta.setSaldoInicial(detalles.getSaldoInicial());
        cuenta.setEstado(detalles.isEstado());
        Cuenta actualizado = cuentaRepository.save(cuenta);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));
        cuentaRepository.delete(cuenta);
        return ResponseEntity.noContent().build();
    }
}
