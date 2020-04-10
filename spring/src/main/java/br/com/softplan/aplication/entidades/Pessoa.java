package br.com.softplan.aplication.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Moquiuti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findCpf", query = "FROM Pessoa WHERE cpf = :p0"),
    @NamedQuery(name = "Pessoa.findId", query = "FROM Pessoa WHERE id = :p0")
})
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Column(name = "nome", length = 150)
    private String nome;

    @NotEmpty(message = "Email não pode ser vazio")
    @Column(name = "email", length = 400)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Imagem imagem;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento")
    private Date nascimento;
    
    @Column(name = "ativo")
    private Boolean ativo;

}
