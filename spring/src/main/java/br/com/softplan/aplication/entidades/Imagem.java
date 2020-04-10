package br.com.softplan.aplication.entidades;

import java.io.Serializable;
import javax.persistence.*;
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
@Table(name = "imagem")
public class Imagem implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "base64")
    private String base64;

}
