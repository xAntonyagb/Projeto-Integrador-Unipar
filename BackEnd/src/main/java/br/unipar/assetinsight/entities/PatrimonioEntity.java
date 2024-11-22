package br.unipar.assetinsight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "PATRIMONIO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioEntity {

    @Id
    @Column(name = "ID_PATRIMONIO")
    private long id;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @JoinColumn(name = "ID_AMBIENTE", nullable = true)
    @ManyToOne
    private AmbienteEntity ambienteEntity;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

}
