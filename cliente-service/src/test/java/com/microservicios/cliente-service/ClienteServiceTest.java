@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void crearClienteTest() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("123");
        cliente.setNombre("Alejandra");
        cliente.setContrasena("1234");
        cliente.setEstado(true);
        Cliente saved = clienteRepository.save(cliente);
        assertNotNull(saved.getId());
    }
}
