import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteService } from '../../../services/ambiente.service';
import { CategoriaService } from '../../../services/categoria.service';
import { CategoriaResponse } from '../../../dtos/responses/Categoria.response';
import { ServicoResponse } from '../../../dtos/responses/Servico.response';
import { OrdemService } from '../../../services/ordem.service';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { ToastrService } from 'ngx-toastr';
import { BlocoResponse} from '../../../dtos/responses/Bloco.response';
import {Subject} from "rxjs";
import {ApiGenericToasts} from "../../../infra/api/api.genericToasts";
import {PatrimonioSimpleResponse} from "../../../dtos/responses/PatrimonioSimple.response";
import {OrdemServicoRequest} from "../../../dtos/requests/OrdemServico.request";
import { ServicoRequest } from '../../../dtos/requests/Servico.request';

@Component({
  selector: 'app-cadastrar-ordem',
  templateUrl: './cadastrar-ordem.component.html',
  styleUrl: './cadastrar-ordem.component.scss'
})
export class CadastrarOrdemComponent implements OnInit {
  @Output() close = new EventEmitter<void>();

  isModalOpen = false;
  descricaoOrdem = '';
  data: Date | any = '';
  bloco: BlocoResponse[] = [];
  categoriasSelecionadas: CategoriaResponse[] = [];
  ambientesDisponiveis: AmbienteResponse[] = [];
  ambientesComServicos: {
    ambiente: any;
    servicos: any[];
  }[] = [];


  novoServico: {
    patrimonio: string,
    servicoDescricao: string,
    quantidade: number,
    valorNumerico: number,
    categoriaSelecionada: string,
    ambientesSelecionados: string,
  } = {
    quantidade: 0,
    patrimonio: '',
    servicoDescricao: '',
    valorNumerico: 0,
    categoriaSelecionada: '',
    ambientesSelecionados: '',
  };

  total = 0;

  constructor(
    private ambiente: AmbienteService,
    private categoria: CategoriaService,
    private ordem: OrdemService,
    private toastr: ToastrService,
    private apiToast: ApiGenericToasts
  ) {
  }

  ngOnInit() {
    this.loadCategorias();
    this.loadAmbientes();
  }

  guardarOrdem() {
    const allServicos = this.ambientesComServicos.flatMap(ambienteGroup => 
      ambienteGroup.servicos.map(servico => ({
        ...servico,
        categoria: servico.categoria.id,
        ambiente: ambienteGroup.ambiente.id
      }))
    );
  
    const ordem: OrdemServicoRequest | any = {
      descricao: this.descricaoOrdem,
      data: this.data,
      servicos: allServicos
    };

    this.ordem.save(ordem).subscribe({
      next: () => {
        this.toastr.success('Ordem cadastrada com sucesso!');
        this.closeModal();
      },
      error: () => {
        this.toastr.error('Erro ao cadastrar ordem');
      }
    });
  }

  loadAmbientes() {
    this.ambiente.getAll(0, 10).subscribe({
      next: (data: any) => {
        this.ambientesDisponiveis = data.content || [];
      },
      error: (error) => {
        this.apiToast.showErro(error);
      }
    });
  }

  loadCategorias() {
    this.categoria.getAll().subscribe({
      next: (data: any) => {
        this.categoriasSelecionadas = data.content || [];
      },
      error: () => {
        this.toastr.error('Erro ao carregar categorias');
      }
    });
  }

  comparaAmbientes(a1: AmbienteResponse, a2: AmbienteResponse): boolean {
    return a1 && a2 ? a1.id === a2.id : a1 === a2;
  }

  adicionarServico(ambienteIndex: number) {
    const categoria = this.categoriasSelecionadas.find(cat => cat.id === +this.novoServico.categoriaSelecionada);
    
    const novoServico = {
      patrimonio: Number(this.novoServico.patrimonio),
      quantidade: this.novoServico.quantidade,
      valorUnit: this.novoServico.valorNumerico,
      valorTotal: this.novoServico.quantidade * this.novoServico.valorNumerico,
      categoria: categoria,
      ambiente: this.ambientesComServicos[ambienteIndex].ambiente
    };
  
    this.ambientesComServicos[ambienteIndex].servicos.push(novoServico);
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
      ambientesSelecionados: '',
    };
  }

  adicionarNovoAmbiente() {
    this.ambientesComServicos.push({
      ambiente: null,
      servicos: []
    });
  }

  calcularTotal() {
    this.total = this.ambientesComServicos.reduce((acc, cur) => {
      return acc + cur.servicos.reduce((sum, servico) => sum + servico.valorTotal, 0);
    }, 0);
  }

  closeModal() {
    this.isModalOpen = false;
    this.close.emit();
  }

  removerServico(ambienteIndex: number, servicoIndex: number) {
    this.ambientesComServicos[ambienteIndex].servicos.splice(servicoIndex, 1);
    this.calcularTotal();
  }

  onQuantidadeChange(event: any, ambienteIndex: number, servicoIndex: number) {
    const quantidade = Number(event.target.value);
    if (quantidade >= 0) {
      this.ambientesComServicos[ambienteIndex].servicos[servicoIndex].quantidade = quantidade;
      this.recalcularSubtotal(ambienteIndex, servicoIndex);
    }
  }
  
  onValorChange(event: any, ambienteIndex: number, servicoIndex: number) {
    const valor = parseFloat(event.target.value);
    if (!isNaN(valor)) {
      this.ambientesComServicos[ambienteIndex].servicos[servicoIndex].valorUnit = valor;
      this.recalcularSubtotal(ambienteIndex, servicoIndex);
    }
  }
  
  recalcularSubtotal(ambienteIndex: number, servicoIndex: number) {
    const servico = this.ambientesComServicos[ambienteIndex].servicos[servicoIndex];
    servico.valorTotal = servico.quantidade * servico.valorUnit;
    this.calcularTotal();
  }
}
