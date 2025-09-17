import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.cuenta_service.entity.Cuenta;
import com.microservicios.cuenta_service.entity.Movimiento;
import com.microservicios.cuenta_service.repository.CuentaRepository;
import com.microservicios.cuenta_service.repository.MovimientoRepository;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getNumeroCuenta())
            .orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no DISPONIBLE");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

         movimiento.setFecha(LocalDate.now()); // <- registra fecha automáticamente
        movimiento.setSaldoDisponible(nuevoSaldo);
        return movimientoRepository.save(movimiento);
    }
}
