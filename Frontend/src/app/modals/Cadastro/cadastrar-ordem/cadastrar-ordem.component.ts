import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteRequest } from '../../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { ServicoResponse } from '../../../dtos/responses/servico.response';
import { OrdemRequest } from '../../../dtos/requests/ordem.request';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { ToastrService } from 'ngx-toastr';
import { BlocoResponse} from '../../../dtos/responses/bloco.response';
import {Subject} from "rxjs";

@Component({
  selector: 'app-cadastrar-ordem',
  templateUrl: './cadastrar-ordem.component.html',
  styleUrl: './cadastrar-ordem.component.scss'
})
export class CadastrarOrdemComponent implements OnInit {
  @Output() close = new EventEmitter<void>();

  isModalOpen = false;
  descricaoOrdem = '';
  data = '';
  bloco: BlocoResponse[] = [];
  servicosAdicionados: ServicoResponse[] = [];
  ambientesSelecionados: AmbienteResponse[] = [];
  categoriasSelecionadas: CategoriaResponse[] = [];


  novoServico: {
    patrimonio: string,
    servicoDescricao: string,
    quantidade: number,
    valorNumerico: number,
    categoriaSelecionada: string,
    ambienteSelecionado: string,
  } = {
    quantidade: 0,
    patrimonio: '',
    servicoDescricao: '',
    valorNumerico: 0,
    categoriaSelecionada: '',
    ambienteSelecionado: '',
  };

  total = 0;

  constructor(
    private ambiente: AmbienteRequest,
    private categoria: CategoriaRequest,
    private ordem: OrdemRequest,
    private toastr: ToastrService,
  ) {
  }

  ngOnInit() {
    this.loadCategorias();
    this.loadAmbientes();
  }

  guardarOrdem() {
    if (!this.ambientesSelecionados.length) {
      this.toastr.warning('Selecione pelo menos um ambiente!');
      return;
    }

    if (!this.servicosAdicionados.length) {
      this.toastr.warning('Adicione pelo menos um serviço!');
      return;
    }

    const ordemData = {
      ambientes: this.ambientesSelecionados,
      servicos: this.servicosAdicionados,
      total: this.total
    };
    this.toastr.success('Ordem cadastrada com sucesso!');
  }

  loadAmbientes() {
    this.ambiente.getAmbientes(0, 10).subscribe({
      next: (data: any) => {
        this.ambientesSelecionados = data.content || [];
      },
      error: (error) => {
        this.toastr.error('Erro ao carregar ambientes');
      }
    });
  }

  loadCategorias() {
    this.categoria.getCategorias().subscribe({
      next: (data: any) => {
        this.categoriasSelecionadas = data.content || [];
      },
      error: () => {
        this.toastr.error('Erro ao carregar categorias');
      }
    });
  }


  adicionarServico() {
    const novoServico: ServicoResponse = {
      patrimonio: this.novoServico.patrimonio,
      descricao: this.novoServico.servicoDescricao,
      quantidade: this.novoServico.quantidade,
      valorUnit: this.novoServico.valorNumerico,
      valorTotal: this.novoServico.quantidade * this.novoServico.valorNumerico,
      categoria: this.categoriasSelecionadas.find(cat => cat.id === +this.novoServico.categoriaSelecionada) || {} as CategoriaResponse,
      ambiente: this.ambientesSelecionados.find(amb => amb.id === +this.novoServico.ambienteSelecionado) || {} as AmbienteResponse,
    };
    this.servicosAdicionados.push(novoServico);
    this.calcularTotal();
    this.resetNovoServico();
  }

  resetNovoServico() {
    this.novoServico = {
      patrimonio: '',
      servicoDescricao: '',
      quantidade: 0,
      valorNumerico: 0,
      categoriaSelecionada: '',
      ambienteSelecionado: '',
    };
  }
  adicionarNovoAmbiente() {
    this.ambientesSelecionados.push({});
  }

  calcularTotal() {
    this.total = this.servicosAdicionados.reduce((acc, cur) => acc + cur.valorTotal, 0);
  }

  guardarAmbiente() {
    const ordem = {
      descricao: this.descricaoOrdem,
      data: this.data,
      servicos: this.servicosAdicionados,
      total: this.total,
    };

    this.ordem.setOrdensServico(ordem).subscribe({
      next: () => {
        this.toastr.success('Ordem cadastrada com sucesso!');
        this.closeModal();
      },
      error: () => {
        this.toastr.error('Erro ao cadastrar ordem');
      }
    });
  }

  closeModal() {
    this.isModalOpen = false;
    this.close.emit();
  }

  removerServico(index: number) {
    this.servicosAdicionados.splice(index, 1);
    this.calcularTotal();
  }

  onQuantidadeChange(event: any, index: number) {
    const quantidade = Number(event.target.value);
    if (quantidade >= 0) {
      this.servicosAdicionados[index].quantidade = quantidade;
      this.recalcularSubtotal(index);
    }
  }

  onValorChange(event: any, index: number) {
    const valor = parseFloat(event.target.value);
    if (!isNaN(valor)) {
      this.servicosAdicionados[index].valorUnit = valor;
      this.recalcularSubtotal(index);
    }
  }
  recalcularSubtotal(index: number) {
    const servico = this.servicosAdicionados[index];
    servico.valorTotal = servico.quantidade * servico.valorUnit;
    this.calcularTotal();
  }
}
