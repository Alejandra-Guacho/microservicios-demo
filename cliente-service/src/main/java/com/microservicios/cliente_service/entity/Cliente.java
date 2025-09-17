import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name="cliente")
public class Cliente extends Persona {
    @Column(unique = true)
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
