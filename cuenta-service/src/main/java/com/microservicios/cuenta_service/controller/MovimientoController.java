@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoServi movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento m = movimientoService.crearMovimiento(movimiento);
            return ResponseEntity.ok(m);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Movimiento> listarMovimientos(
        @RequestParam(required = false) Long cuentaId,
        @RequestParam(required = false) Long clienteId) {
    // Lógica para devolver movimientos filtrados por cuenta o cliente
}

}
