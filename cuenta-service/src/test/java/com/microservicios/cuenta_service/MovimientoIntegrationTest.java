@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovimientoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCrearMovimiento() throws Exception {
        String movimientoJson = "{ \"valor\": 100, \"tipoMovimiento\": \"DEPOSITO\", \"cuenta\": {\"numeroCuenta\": 478758} }";
        mockMvc.perform(post("/movimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(movimientoJson))
                .andExpect(status().isOk());
    }
}
