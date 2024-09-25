package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "TAREFA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TAREFA")
    private long id;

    @Column(name = "DS_TITULO")
    private String titulo;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @Column(name = "DT_PREVISAO")
    private Timestamp dtPrevisao;

    @ManyToOne
    @JoinColumn(name = "ID_AMBIENTE")
    private AmbienteEntity ambienteEntity;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private CategoriaEntity categoriaEntity;

    @Column(name = "DS_PRIORIDADE")
    @Enumerated(EnumType.STRING)
    private PrioridadeEnum prioridade;

    @Column(name = "DS_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusTarefaEnum status;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

    @Column(name = "IS_ARQUIVADO")
    @ColumnDefault("true")
    private boolean arquivado;
}
