package com.microservicios.cuenta_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Table(name="movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoDisponible;

    @ManyToOne
    @JoinColumn(name="cuenta_id")
    private Long cuentaId;
    
    private Cuenta cuenta;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public LocalDate getFecha() { return fecha; }
public void setFecha(LocalDate fecha) { this.fecha = fecha; }

public String getTipoMovimiento() { return tipoMovimiento; }
public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

public Double getValor() { return valor; }
public void setValor(Double valor) { this.valor = valor; }

public Double getSaldoDisponible() { return saldoDisponible; }
public void setSaldoDisponible(Double saldoDisponible) { this.saldoDisponible = saldoDisponible; }

public Long getCuentaId() { return cuentaId; }
public void setCuentaId(Long cuentaId) { this.cuentaId = cuentaId; }

 public Cuenta getCuenta() { return cuenta; }
public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }

}
