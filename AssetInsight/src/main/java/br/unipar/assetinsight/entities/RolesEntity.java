package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.enums.PermissoesEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "ROLE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolesEntity {

    @Id
    @Column(name = "ID_ROLE")
    private long id;

    @Column(name = "DS_PERMISSAO")
    @Enumerated(EnumType.STRING)
    private PermissoesEnum permisao;
}
