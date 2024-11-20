import { Component } from '@angular/core';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent {

  donutData1 = {
    labels: ['Red', 'Blue', 'Yellow'],
    datasets: [
      {
        data: [300, 50, 100],
        backgroundColor: ['#FF0000', '#0000FF', '#FFFF00'],
        hoverBackgroundColor: ['#FF4D4D', '#4D4DFF', '#FFFF66']
      }
    ]
  };


  donutData2 = {
    labels: ['Green', 'Purple', 'Orange'],
    datasets: [
      {
        data: [150, 200, 250],
        backgroundColor: ['#00FF00', '#800080', '#FFA500'],
        hoverBackgroundColor: ['#66FF66', '#9933FF', '#FFB84D']
      }
    ]
  };


  donutData3 = {
    labels: ['Blue', 'Red', 'Gray'],
    datasets: [
      {
        data: [120, 300, 180],
        backgroundColor: ['#0000FF', '#FF69B4', '#808080'],
        hoverBackgroundColor: ['#66B3FF', '#FFB3D9', '#D3D3D3']
      }
    ]
  };


  donutOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top'
      },
      tooltip: {
        callbacks: {
          label: function(tooltipItem: any) {
            return tooltipItem.label + ': ' + tooltipItem.raw + '%';
          }
        }
      }
    }
  };


  lineChartData = {
    labels: ['January', 'February', 'March', 'April', 'May'],
    datasets: [
      {
        label: 'Sales Over Time',
        data: [65, 59, 80, 81, 56],  // Exemplo de dados para o gráfico de linha
        fill: false,  // Linha sem preenchimento
        borderColor: '#42A5F5',
        tension: 0.1
      }
    ]
  };

  lineChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'top'
      },
      tooltip: {
        callbacks: {
          label: function(tooltipItem: any) {
            return tooltipItem.label + ': ' + tooltipItem.raw;
          }
        }
      }
    },
    scales: {
      x: {
        beginAtZero: true
      },
      y: {
        beginAtZero: true
      }
    }
  };
}
