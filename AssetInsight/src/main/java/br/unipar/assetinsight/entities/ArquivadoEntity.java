package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "ARQUIVADO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArquivadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARQUIVADO")
    private long id;

    @Column(name = "DS_TIPO")
    @Enumerated(EnumType.STRING)
    private TipoArquivadoEnum tipo;

    @ManyToOne
    @JoinColumn(name = "ID_ORDEM_SERVICO")
    private OrdemServicoEntity ordemServicoEntity;

    @ManyToOne
    @JoinColumn(name = "ID_TAREFA")
    private TarefaEntity tarefaEntity;

    @Column(name = "DT_EXCLUIR")
    private Timestamp dtExcluir;

    @Column(name = "DT_ARQUIVADO")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtArquivado;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_RESPONSAVEL")
    private UsuarioEntity usuarioEntityResponsavel;

}