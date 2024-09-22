package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.enums.NotificacaoEnum;
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
public class NotificacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BLOCO")
    private long id;

    @Column(name = "DS_TIPO")
    @Enumerated(EnumType.STRING)
    private NotificacaoEnum tipo;

    @Column(name = "DS_TITULO")
    private String titulo;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

}
