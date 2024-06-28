package dupradosantini.sostoolbackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo nome é obrigatório")
    @Length(min = 3, max = 60, message = "O nome deve entre 3 e 30 carateres")
    private String name;

    @NotEmpty(message = "Campo ativo é obrigatório")
    private boolean active;

    @Length(max=50, message = "Tipo pode ter no maximo 50 carácteres")
    private String type;

    public Filter(String name, boolean active, String type) {
        this.name = name;
        this.active = active;
        this.type = type;
    }
}
