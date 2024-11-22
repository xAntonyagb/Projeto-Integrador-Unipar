import {Component, OnInit} from '@angular/core';
import {RelatoriosService} from "../../services/relatorios.service";
import {RelatoriosMensaisResponse} from "../../dtos/responses/relatorios/RelatoriosMensais.response";
import {RelatoriosAnoResponse} from "../../dtos/responses/relatorios/RelatoriosAno.response";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit{
  osPreenchidas: number = 0;
  tarefasConcluidas: number = 0;
  mediaGastos: number = 0;
  gastosMes: number = 0;
  patrimonioMaiorNumeroServicos: number | null = null;
  blocoMaiorNumeroServicos: string | null = null;
  patrimonioMaiorNumeroServicosAno: number | null = null;
  blocoMaiorNumeroServicosAno: string | null = null;
  servicosRealizadosAno: number = 0;
  totalGastosAno: number = 0;


  donutData1: any;
  donutData2: any;
  donutData3: any;
  donutData4: any;
  donutData5: any;
  donutData6: any;
  lineChartData: any;

  meses = [
    { value: 1, label: 'Janeiro' }, { value: 2, label: 'Fevereiro' },
    { value: 3, label: 'Março' }, { value: 4, label: 'Abril' },
    { value: 5, label: 'Maio' }, { value: 6, label: 'Junho' },
    { value: 7, label: 'Julho' }, { value: 8, label: 'Agosto' },
    { value: 9, label: 'Setembro' }, { value: 10, label: 'Outubro' },
    { value: 11, label: 'Novembro' }, { value: 12, label: 'Dezembro' },
  ];
  selectedMes = new Date().getMonth() + 1;

  constructor(private relatoriosService: RelatoriosService) {}

  ngOnInit() {
    this.fetchDados();
  }

  fetchDados() {
    this.relatoriosService.getAll(this.selectedMes).subscribe((response) => {
      const relatoriosGerais = response.relatoriosGerais;
      this.osPreenchidas = relatoriosGerais.ordensServicoPreenchidas.preenchidas;
      this.tarefasConcluidas = relatoriosGerais.tarefasConcluidas.concluidas;
      this.mediaGastos = relatoriosGerais.mediaGastosMes;
      this.gastosMes = relatoriosGerais.gastosMes.valorGasto;


      const relatoriosMensais = response.relatoriosMensais;
      this.patrimonioMaiorNumeroServicos = relatoriosMensais.topPatrimonioMes.patrimonio || null;
      this.blocoMaiorNumeroServicos = relatoriosMensais.topBlocoMes.bloco ||  null;


      const relatoriosAnuais = response.relatoriosAnuais;
      this.patrimonioMaiorNumeroServicosAno = relatoriosAnuais.topPatrimonioAno.patrimonio ||  null;
      this.blocoMaiorNumeroServicosAno = relatoriosAnuais.topBlocoAno.bloco ||  null;
      this.servicosRealizadosAno = relatoriosAnuais.qtdServicosAno;
      this.totalGastosAno = relatoriosAnuais.totalGastoOS;


      this.donutData1 = this.formatarGrafico(relatoriosMensais.graficos[0]);
      this.donutData2 = this.formatarGrafico(relatoriosMensais.graficos[1]);
      this.donutData3 = this.formatarGrafico(relatoriosMensais.graficos[2]);

      this.donutData4 = this.formatarGrafico(relatoriosAnuais.graficos[0]);
      this.donutData5 = this.formatarGrafico(relatoriosAnuais.graficos[1]);
      this.donutData6 = this.formatarGrafico(relatoriosAnuais.graficos[2]);


      this.lineChartData = this.formatarGrafico(relatoriosAnuais.graficos[3]);
    });
  }


  onMesChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const selectedValue = Number(target.value);
    this.selectedMes = selectedValue;
    this.fetchDados();
  }
  donutOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          font: {
            size: 14,
          },
          color: '#333',
        },
      },
      tooltip: {
        enabled: true,
        callbacks: {
          label: function (tooltipItem: any) {
            const value = tooltipItem.raw;
            return ` ${value} unidades`;
          },
        },
      },
    },
  };
  lineChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        grid: {
          display: true,
          color: '#f0f0f0',
        },
        title: {
          display: true,
          text: 'Meses',
          color: '#333',
          font: {
            size: 14,
          },
        },
      },
      y: {
        grid: {
          display: true,
          color: '#e0e0e0',
        },
        title: {
          display: true,
          text: 'Quantidade',
          color: '#333',
          font: {
            size: 14,
          },
        },
      },
    },
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: true,
        callbacks: {
          label: function (tooltipItem: any) {
            return ` ${tooltipItem.raw} unidades`;
          },
        },
      },
    },
  };
  formatarGrafico(grafico: any) {
    if (!grafico || !grafico.dados || Object.keys(grafico.dados).length === 0) {
      return {
        labels: ['N/A'],
        datasets: [
          {
            data: [ 1 ],
            backgroundColor: ['#36A2EB'],
            hoverBackgroundColor: ['#36A2EB'],
          },
        ],
      };
    }

    return {
      labels: Object.keys(grafico.dados),
      datasets: [
        {
          data: Object.values(grafico.dados),
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'], // Customize as cores aqui
          hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        },
      ],
    };
  }
}
