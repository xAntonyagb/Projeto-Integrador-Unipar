package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.entities.keys.ServicoEntityKey;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "SERVICO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ServicoEntityKey.class)
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private long idServico;

    @Id
    @Column(name = "ID_ORDEM_SERVICO")
    private long idOrdemServico;

    @Column(name = "CD_PATRIMONIO")
    private String patrimonio;

    @Column(name = "DS_PATRIMONIO")
    private String descricaoPatrimonio;

    @Column(name = "NR_QUANTIDADE")
    private long quantidade;

    @Column(name = "VL_UNITARIO")
    private double valorUnit;

    @Column(name = "VL_TOTAL")
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private CategoriaEntity categoriaEntity;

    @ManyToOne
    @JoinColumn(name = "ID_AMBIENTE")
    private AmbienteEntity ambienteEntity;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIADOR")
    private UsuarioEntity usuarioEntityCriador;

}
