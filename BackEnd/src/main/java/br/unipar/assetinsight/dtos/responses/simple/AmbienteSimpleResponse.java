package br.unipar.assetinsight.dtos.responses.simple;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO de {@link br.unipar.assetinsight.entities.AmbienteEntity} com apenas id e descrição.
 */
public record AmbienteSimpleResponse(
    @Schema(description = "Id do ambiente.", example = "1")
    long id,

    @Schema(description = "Nome do ambiente.", example = "Sala 01")
    String descricao
) implements Serializable { }
