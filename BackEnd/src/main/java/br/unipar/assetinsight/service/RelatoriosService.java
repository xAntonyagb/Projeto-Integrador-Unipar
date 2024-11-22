package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.responses.principal.relatorios.*;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import br.unipar.assetinsight.enums.TipoGraficosEnum;
import br.unipar.assetinsight.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RelatoriosService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final TarefaRepository tarefaRepository;
    private final PatrimonioRepository patrimonioRepository;
    private final ServicoRepository servicoRepository;
    private final BlocoRepository blocoRepository;

    public RelatoriosGeraisResponse getGeral() {
        long osPreenchidas = ordemServicoRepository.countByStatus(StatusOrdemServicoEnum.PREENCHIDA);
        long osTotal = ordemServicoRepository.count();
        long osPendentes = osTotal - osPreenchidas;
        OrdensServicoPreenchidasResponse ordensServicoPreenchidas = new OrdensServicoPreenchidasResponse(osPreenchidas, osTotal, osPendentes);

        long tConcluidas = tarefaRepository.countTarefasByStatus(StatusTarefaEnum.CONCLUIDA);
        long tTotal = tarefaRepository.count();
        long tRestantes = tTotal == 0 ? 0 : tTotal - tConcluidas;
        TarefasConcluidasResponse tarefasConcluidas = new TarefasConcluidasResponse(tConcluidas, tTotal, tRestantes);

        List<Double> osGastos = new ArrayList<>();
        List<Integer> osMeses = ordemServicoRepository.findAllMeses();
        for (int mes : osMeses) {
            Double retorno = ordemServicoRepository.sumValorGastoByMonth(mes);
            osGastos.add(retorno == null ? 0 : retorno);
        }

        double mediaGastosMes = (osGastos.isEmpty())
                ? 0
                : osGastos.stream().mapToDouble(Double::doubleValue).average().orElse(0);


        Double valorGasto = ordemServicoRepository.sumValorGastoByMonth(LocalDateTime.now().getMonthValue());
        valorGasto = valorGasto == null ? 0 : valorGasto;

        double mediaMes = (valorGasto * 100) / mediaGastosMes;
        GastosMesResponse gastosMes = new GastosMesResponse(valorGasto, mediaMes);

        return new RelatoriosGeraisResponse(ordensServicoPreenchidas, tarefasConcluidas, mediaGastosMes, gastosMes);
    }

    public RelatoriosMensaisResponse getMensal(Integer mes) {
        List<GraficoResponse> graficos = new ArrayList<>();
        TipoGraficosEnum tipoGraficoPizza = TipoGraficosEnum.PIZZA;

        String tituloAmbiente = "Gastos do mês em ambients";
        Map<String, Double> dadosAmbientes = mesclaListaMap(ordemServicoRepository.findSumValorTotalByAmbienteAndMonth(mes));
        GraficoResponse graficoAmbientes = new GraficoResponse(tituloAmbiente, tipoGraficoPizza, dadosAmbientes);

        String tituloCategorias = "Gastos do mês em gategorias";
        Map<String, Double> dadosCategorias = mesclaListaMap(ordemServicoRepository.findSumValorTotalByCategoriaAndMonth(mes));
        GraficoResponse graficoCategorias = new GraficoResponse(tituloCategorias, tipoGraficoPizza, dadosCategorias);

        String tituloPatrimonios = "Gastos do mês em patrimônios";
        Map<String, Double> dadosPatrimonios = mesclaListaMap(ordemServicoRepository.findSumValorTotalByPatrimonioAndMonth(mes));
        GraficoResponse graficoPatrimonios = new GraficoResponse(tituloPatrimonios, tipoGraficoPizza, dadosPatrimonios);

        graficos.add(graficoAmbientes);
        graficos.add(graficoCategorias);
        graficos.add(graficoPatrimonios);

        Optional<PatrimonioEntity> retornoTopPatrimonio = patrimonioRepository.findTopPatrimonioByMonth(mes);
        Long topPatrimonio = retornoTopPatrimonio.isPresent() ? retornoTopPatrimonio.get().getId() : null;

        long countServicos = topPatrimonio == null ? 0 : servicoRepository.countByPatrimonioEntity_Id(topPatrimonio);
        PatrimonioServicoResponse topPatrimonioMes = new PatrimonioServicoResponse(topPatrimonio, countServicos);

        Optional<BlocoEntity> retornoTopBloco = blocoRepository.findTopBlocoByMonth(mes);
        BlocoEntity topBloco = retornoTopBloco.orElse(null);

        long countServicosBloco = topBloco == null ? 0 : servicoRepository.countByAmbienteEntity_BlocoEntity_Id(topBloco.getId());
        BlocoServicoResponse topBlocoMes = new BlocoServicoResponse(topBloco == null ? null : topBloco.getDescricao(), countServicosBloco);

        return new RelatoriosMensaisResponse(graficos, topPatrimonioMes, topBlocoMes);
    }

    public RelatoriosAnoResponse getAnual(Integer ano) {
        List<GraficoResponse> graficos = new ArrayList<>();
        TipoGraficosEnum tipoGraficoPizza = TipoGraficosEnum.PIZZA;
        TipoGraficosEnum tipoGraficoBarra = TipoGraficosEnum.LINHA;

        String tituloAmbiente = "Gastos anuais em ambients";
        Map<String, Double> dadosAmbientes = mesclaListaMap(ordemServicoRepository.findSumValorTotalByAmbienteAndYear(ano));
        GraficoResponse graficoAmbientes = new GraficoResponse(tituloAmbiente, tipoGraficoPizza, dadosAmbientes);

        String tituloCategorias = "Gastos anuais em gategorias";
        Map<String, Double> dadosCategorias = mesclaListaMap(ordemServicoRepository.findSumValorTotalByCategoriaAndYear(ano));
        GraficoResponse graficoCategorias = new GraficoResponse(tituloCategorias, tipoGraficoPizza, dadosCategorias);

        String tituloPatrimonios = "Gastos anuais em patrimônios";
        Map<String, Double> dadosPatrimonios = mesclaListaMap(ordemServicoRepository.findSumValorTotalByPatrimonioAndYear(ano));
        GraficoResponse graficoPatrimonios = new GraficoResponse(tituloPatrimonios, tipoGraficoPizza, dadosPatrimonios);

        String tituloGastosAnuais = "Gastos anuais em Ordens de Serviço";
        Map<String, Double> dadosGastosAnuais = mesclaListaMap(ordemServicoRepository.findSumValorTotalByMonthAndYear(ano));
        GraficoResponse graficoGastosAnuais = new GraficoResponse(tituloGastosAnuais, tipoGraficoBarra, dadosGastosAnuais);

        graficos.add(graficoAmbientes);
        graficos.add(graficoCategorias);
        graficos.add(graficoPatrimonios);
        graficos.add(graficoGastosAnuais);

        Double totalGastoOS = ordemServicoRepository.findTotalGastoByYear(ano);
        double qtdServicosAno = servicoRepository.countServicosByYear(ano);

        Optional<PatrimonioEntity> retornoTopPatrimonio = patrimonioRepository.findTopPatrimonioByYear(ano);
        Long topPatrimonio = retornoTopPatrimonio.isPresent() ? retornoTopPatrimonio.get().getId() : null;

        long countServicos = topPatrimonio == null ? 0 : servicoRepository.countByPatrimonioEntity_Id(topPatrimonio);
        PatrimonioServicoResponse topPatrimonioAno = new PatrimonioServicoResponse(topPatrimonio, countServicos);

        Optional<BlocoEntity> retornoTopBloco = blocoRepository.findTopBlocoByYear(ano);
        BlocoEntity topBloco = retornoTopBloco.orElse(null);

        long countServicosBloco = topBloco == null ? 0 : servicoRepository.countByAmbienteEntity_BlocoEntity_Id(topBloco.getId());
        BlocoServicoResponse topBlocoAno = new BlocoServicoResponse(topBloco == null ? null : topBloco.getDescricao(), countServicosBloco);

        return new RelatoriosAnoResponse(graficos, topPatrimonioAno, topBlocoAno, qtdServicosAno, totalGastoOS);
    }

    public Map<String, Double> mesclaListaMap(List<Map<Object, Object>> results) {
        Map<String, Double> mappedResults = results.stream().collect(Collectors.toMap(
                result -> (String) result.get("param1").toString(),
                result -> (Double) result.get("totalValor")
        ));
        return mappedResults;
    }

    public RelatoriosFullResponse getAll(Integer mes, Integer ano) {
        RelatoriosGeraisResponse relatoriosGerais = getGeral();
        RelatoriosMensaisResponse relatoriosMensais = getMensal(mes);
        RelatoriosAnoResponse relatoriosAnuais = getAnual(ano);

        return new RelatoriosFullResponse(relatoriosGerais, relatoriosMensais, relatoriosAnuais);
    }
}
