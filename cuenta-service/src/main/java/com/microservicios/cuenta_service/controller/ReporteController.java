package com.microservicios.cuenta_service.controller;

import com.microservicios.cuenta_service.entity.Cuenta;
import com.microservicios.cuenta_service.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerReporte(
            @RequestParam Long clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        List<Cuenta> cuentas = cuentaService.generarReporte(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(cuentas);
    }
}
