package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.principal.BlocoResponse;
import br.unipar.assetinsight.dtos.responses.principal.relatorios.RelatoriosAnoResponse;
import br.unipar.assetinsight.dtos.responses.principal.relatorios.RelatoriosFullResponse;
import br.unipar.assetinsight.dtos.responses.principal.relatorios.RelatoriosGeraisResponse;
import br.unipar.assetinsight.dtos.responses.principal.relatorios.RelatoriosMensaisResponse;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.service.RelatoriosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("relatorios")
@Tag(name = "Relatórios", description = "Endpoints para obtenção dos dados de relatórios.")
public class RelatoriosController {

    private final RelatoriosService relatoriosService;

    @Operation(summary = "Retorna os relatórios gerais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RelatoriosGeraisResponse.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping("/geral")
    public ResponseEntity<RelatoriosGeraisResponse> getGeral() {
        RelatoriosGeraisResponse relatoriosGeraisResponse = relatoriosService.getGeral();
        return ResponseEntity.ok(relatoriosGeraisResponse);
    }

    @Operation(summary = "Retorna os relatórios mensais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RelatoriosMensaisResponse.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @Parameters({
            @Parameter(name = "mes", description = "Mês para buscar os dados.", required = false, example = "1")
    })
    @GetMapping("/mensal")
    public ResponseEntity<RelatoriosMensaisResponse> getMensal(@RequestParam(required = false) Integer mes) {
        if (mes == null) {
            mes = LocalDate.now().getMonthValue();
        }

        RelatoriosMensaisResponse relatoriosMensaisResponse = relatoriosService.getMensal(mes);
        return ResponseEntity.ok(relatoriosMensaisResponse);
    }


    @Operation(summary = "Retorna os relatórios anuais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RelatoriosAnoResponse.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @Parameters({
            @Parameter(name = "ano", description = "Ano para buscar os dados.", required = false, example = "2024")
    })
    @GetMapping("/anual")
    public ResponseEntity<RelatoriosAnoResponse> getAnual(@RequestParam(required = false) Integer ano) {
        if (ano == null) {
            ano = LocalDate.now().getYear();
        }

        RelatoriosAnoResponse relatoriosAnoResponse = relatoriosService.getAnual(ano);
        return ResponseEntity.ok(relatoriosAnoResponse);
    }


    @Operation(summary = "Retorna todos os relatórios de uma vez.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RelatoriosFullResponse.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @Parameters({
            @Parameter(name = "mes", description = "Mês para buscar os dados dos relatórios mensais.", required = false, example = "1"),
            @Parameter(name = "ano", description = "Ano para buscar os dados dos relatórios anuais.", required = false, example = "2024")
    })
    @GetMapping("/all")
    public ResponseEntity<RelatoriosFullResponse> getAll(@RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer ano) {
        if (mes == null) {
            mes = LocalDate.now().getMonthValue();
        }
        if (ano == null) {
            ano = LocalDate.now().getYear();
        }

        RelatoriosFullResponse relatoriosFullResponse = relatoriosService.getAll(mes, ano);
        return ResponseEntity.ok(relatoriosFullResponse);
    }

}
