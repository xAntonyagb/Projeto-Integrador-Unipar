import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteService } from '../../../services/ambiente.service';
import { CategoriaService } from '../../../services/categoria.service';
import { CategoriaResponse } from '../../../dtos/responses/Categoria.response';
import { OrdemService } from '../../../services/ordem.service';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { ToastrService } from 'ngx-toastr';
import { BlocoResponse} from '../../../dtos/responses/Bloco.response';
import {ApiGenericToasts} from "../../../infra/api/api.genericToasts";
import {OrdemServicoRequest} from "../../../dtos/requests/OrdemServico.request";
import { PatrimonioResponse } from '../../../dtos/responses/Patrimonio.response';
import { PatrimonioService } from '../../../services/patrimonio.service';

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
  patrimoniosDisponiveis: PatrimonioResponse[] = [];
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
    valorTotal: number
  } = {
    quantidade: 0,
    patrimonio: '',
    servicoDescricao: '',
    valorNumerico: 0,
    categoriaSelecionada: '',
    ambientesSelecionados: '',
    valorTotal: 0
  };

  total = 0;

  constructor(
    private ambiente: AmbienteService,
    private categoria: CategoriaService,
    private patrimonio: PatrimonioService,
    private ordem: OrdemService,
    private toastr: ToastrService,
    private apiToast: ApiGenericToasts
  ) {
  }

  ngOnInit() {
    this.loadCategorias();
    this.loadAmbientes();
    this.loadPatrimonios();
  }

  guardarOrdem() {
    const allServicos = this.ambientesComServicos.flatMap(ambienteGroup => 
      ambienteGroup.servicos.map(servico => ({
        descricao: servico.descricao,
        quantidade: servico.quantidade,
        valorUnit: servico.valorUnit,
        valorTotal: servico.valorTotal,
        categoria: servico.categoria?.id,
        ambiente: ambienteGroup?.ambiente?.id,
        patrimonio: servico.patrimonio?.patrimonio
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

  loadPatrimonios() {
    this.patrimonio.getAll().subscribe({
      next: (data: any) => {
        this.patrimoniosDisponiveis = data.content || [];
      },
      error: () => {
        this.toastr.error('Erro ao carregar patrimonios');
      }
    });
  }

  comparaAmbientes(a1: AmbienteResponse, a2: AmbienteResponse): boolean {
    return a1 && a2 ? a1.id === a2.id : a1 === a2;
  }

  comparaCategorias(c1: CategoriaResponse, c2: CategoriaResponse): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  comparaPatrimonios(p1: PatrimonioResponse, p2: PatrimonioResponse): boolean {
    return p1 && p2 ? p1.patrimonio === p2.patrimonio : p1 === p2;
  }

  adicionarServico(ambienteIndex: number) {
    const novoServico = {
      descricao: '',
      patrimonio: Number(this.novoServico.patrimonio),
      quantidade: this.novoServico.quantidade,
      valorUnit: this.novoServico.valorNumerico,
      valorTotal: this.novoServico.quantidade * this.novoServico.valorNumerico,
      categoria: { } as CategoriaResponse,
      ambiente: this.ambientesComServicos[ambienteIndex].ambiente,
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
      valorTotal: 0
    };
  }

  adicionarNovoAmbiente() {
    this.ambientesComServicos.push({
      ambiente: null,
      servicos: []
    });
  }

  calcularTotal() {
    this.total = this.ambientesComServicos.reduce((acc, ambiente) => {
      return acc + ambiente.servicos.reduce((sum, servico) => {
        return sum + (servico.quantidade || 0) * (servico.valorUnit || 0);
      }, 0);
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
    this.ambientesComServicos[ambienteIndex].servicos[servicoIndex].valorTotal = servico.quantidade * servico.valorUnit;
    this.calcularTotal();
  }

  removerAmbiente(ambienteIndex: number) {
    this.ambientesComServicos.splice(ambienteIndex, 1);
    this.calcularTotal();
  }
}
