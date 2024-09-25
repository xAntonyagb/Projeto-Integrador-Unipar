package br.unipar.assetinsight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "BLOCO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlocoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_BLOCO")
    private Long id;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

}
