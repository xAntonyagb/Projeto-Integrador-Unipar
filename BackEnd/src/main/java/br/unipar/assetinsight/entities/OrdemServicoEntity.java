package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ORDEM_SERVICO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ORDEM_SERVICO")
    private long id;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @Column(name = "DT_ORDEM_SERVICO")
    private Timestamp data;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDEM_SERVICO")
    private List<ServicoEntity> listServicoEntities;

    @Column(name = "VL_TOTAL")
    private double valorTotal;

    @Column(name = "DS_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusOrdemServicoEnum status;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @Column(name = "IS_ARQUIVADO")
    @ColumnDefault("false")
    private boolean arquivado;
}