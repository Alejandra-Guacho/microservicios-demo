package com.microservicios.cuenta_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name="cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="cliente_id")
     private Long clienteId;
    //private Cliente cliente;

public Long getNumeroCuenta() { return numeroCuenta; }
public void setNumeroCuenta(Long numeroCuenta) { this.numeroCuenta = numeroCuenta; }

public String getTipoCuenta() { return tipoCuenta; }
public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

public Double getSaldoInicial() { return saldoInicial; }
public void setSaldoInicial(Double saldoInicial) { this.saldoInicial = saldoInicial; }

public Boolean isEstado() { return estado; }
public void setEstado(Boolean estado) { this.estado = estado; }

  public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}
