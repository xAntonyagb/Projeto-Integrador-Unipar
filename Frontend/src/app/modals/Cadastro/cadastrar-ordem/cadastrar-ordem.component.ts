import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteRequest } from '../../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { ServicoResponse } from '../../../dtos/responses/servico.response';
import { OrdemRequest } from '../../../dtos/requests/ordem.request';
import { ModalRequest } from '../../../dtos/requests/modal.request';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { ToastrService } from 'ngx-toastr';
import { BlocoResponse } from '../../../dtos/responses/bloco.response';
import {Subject} from "rxjs";

@Component({
  selector: 'app-cadastrar-ordem',
  templateUrl: './cadastrar-ordem.component.html',
  styleUrl: './cadastrar-ordem.component.scss'
})
export class CadastrarOrdemComponent implements OnInit {
  isModalOpen = true;
  descricaoOrdem = '';
  data = '';
  subtotal = 0;
  bloco: BlocoResponse[] = [];
  servicosAdicionados: ServicoResponse[] = [];
  ambientesSelecionados: any[] = [];
  categoriasSelecionadas: any[] = [];
  private destroy$ = new Subject<void>();

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
    private modalRequest: ModalRequest,
    private toastr: ToastrService,
  ) {
  }

  ngOnInit() {
    this.modalRequest.isOpen$.subscribe(isOpen => {
      this.isModalOpen = isOpen;
      this.ambientesSelecionados = [
        { id: 1, descricao: 'Ambiente 1' },
        { id: 2, descricao: 'Ambiente 2' },
      ];
    this.loadCategorias();
    this.loadAmbientes();
    });
  }

  loadAmbientes() {
    this.ambiente.getAmbientes().subscribe({
      next: (data: any) => {
        console.log('Ambientes retornados:', data);
        this.ambientesSelecionados = data.content || []; // Extraia apenas o conteúdo
      },
      error: (error) => {
        console.error('Erro ao carregar ambientes:', error);
        this.toastr.error('Erro ao carregar ambientes');
      }
    });
  }

  loadCategorias() {
    this.categoria.getCategorias().subscribe({
      next: (data: any) => {
        console.log('Categorias retornadas:', data);
        this.categoriasSelecionadas = data.content || []; // Extraia apenas o conteúdo
      },
      error: (error) => {
        console.error('Erro ao carregar categorias:', error);
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

  removerUltimoAmbiente() {
    if (this.ambientesSelecionados.length > 1) {
      const ambienteRemovido = this.ambientesSelecionados.pop();
      this.servicosAdicionados = this.servicosAdicionados.filter(servico => servico.ambiente.id !== ambienteRemovido?.id);
      this.calcularTotal();
    } else {
      this.servicosAdicionados = [];
      this.calcularTotal();
    }
  }

  adicionarNovoAmbiente() {
    const novoAmbiente: AmbienteResponse = {
      id: this.ambientesSelecionados.length + 1,
      descricao: `Ambiente ${this.ambientesSelecionados.length + 1}`,
      bloco: this.bloco[0],
      qtdPatrimonios: 0,
      servicos: []
    };
    this.ambientesSelecionados.push(novoAmbiente);
  }

  calcularTotal() {
    this.total = this.servicosAdicionados.reduce((acc, cur) => acc + cur.valorTotal, 0);
  }

  calcularSubtotal() {
    this.subtotal = this.novoServico.quantidade * this.novoServico.valorNumerico;
    this.calcularTotal();
  }

  guardarAmbiente() {
    const ordemData = {
      descricao: this.descricaoOrdem,
      data: this.data,
      servicos: this.servicosAdicionados,
      total: this.total,
    };

    this.ordem.setOrdensServico(ordemData).subscribe({
      next: () => {
        this.toastr.success('Ordem Adicionada Com Sucesso', 'Cadastro Concluído');
        this.closeModal();
      },
      error: error => {
        this.toastr.error('Ocorreu Um Erro Ao Cadastrar Sua Ordem', 'Erro ao Cadastrar');
        console.error('Erro ao cadastrar ordem de serviço:', error);
      }
    });
  }

  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }

  removerServico(index: number) {
    this.servicosAdicionados.splice(index, 1);
    this.calcularTotal();
  }

  onQuantidadeChange(event: any, index: number) {
    const valor = Number(event.target.value);
    if (valor >= 0) {
      this.servicosAdicionados[index].quantidade = valor;
      this.calcularSubtotal();
    }
  }

  onValorChange(event: any, index: number) {
    let valorDigitado = event.target.value;
    valorDigitado = valorDigitado.replace(/[^\d,]/g, '').replace(',', '.');
    const valorNumerico = parseFloat(valorDigitado);

    if (!isNaN(valorNumerico)) {
      this.servicosAdicionados[index].valorUnit = valorNumerico;
      this.servicosAdicionados[index].valorTotal = this.servicosAdicionados[index].quantidade * valorNumerico;
    }
    this.calcularTotal();
  }
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
