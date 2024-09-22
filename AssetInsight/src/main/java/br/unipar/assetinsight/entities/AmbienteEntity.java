package br.unipar.assetinsight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "AMBIENTE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AmbienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AMBIENTE")
    private long id;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_BLOCO")
    private BlocoEntity blocoEntity;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

}
