import { Component, EventEmitter, inject, Inject, OnInit, Output } from '@angular/core';
import { AmbienteRequest } from '../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../dtos/requests/categoria.request';
import { ServicoRequest } from '../../dtos/requests/servico.request';
import { CategoriaResponse } from '../../dtos/responses/categoria.response';
import { ServicoResponse } from '../../dtos/responses/servico.response';
import { OrdemRequest } from '../../dtos/requests/ordem.request';
import { OrdemResponse } from '../../dtos/responses/ordem.response';
import { ModalRequest } from '../../dtos/requests/modal.request';
import { AmbienteResponse } from '../../dtos/responses/ambiente.response';
import { BlocoRequest } from '../../dtos/requests/bloco.request';

@Component({
  selector: 'app-cadastrar-ordem',
  templateUrl: './cadastrar-ordem.component.html',
  styleUrl: './cadastrar-ordem.component.scss'
})
export class CadastrarOrdemComponent  implements OnInit{
  
  isModalOpen = true;
  descricao = '';
  data = '';
  quantidade : number = 0;
  patrimonio = '';
  valor :string = '';
  valorNumerico :number = 0;
  subtotal = 0;
  total = 0;
  categoriaSelecionada = '';
  ambienteSelecionado = '';
  servicosAdicionados: any[] = [];
  ambientes: AmbienteResponse[] = [];
  categorias: CategoriaResponse[] = [];
  servicoDescricao = '';
  

  constructor(
    private ambiente: AmbienteRequest,
    private categoria : CategoriaRequest,
    private ordem : OrdemRequest,
    private modalRequest :ModalRequest
    ){}

    ngOnInit() {
      this.loadAmbientes();
      this.loadCategorias();
      this.modalRequest.isOpen$.subscribe( isOpen =>{
      this.isModalOpen = isOpen;
      });
    }
    loadAmbientes() {
      this.ambiente.getAmbientes().subscribe((data: any) => {
        this.ambientes = data;
      });
    }
  
    loadCategorias() {
      this.categoria.getCategorias().subscribe((data: CategoriaResponse[]) => {
      this.categorias = data;
      });
    }

  calcularSubtotal() {
    this.subtotal = this.quantidade * this.valorNumerico;
    this.calcularTotal();
  }

  adicionarServico() {
    const novoServico: ServicoResponse = {
      id: Date.now(), // Generate a unique ID for the new service
      patrimonio: this.patrimonio,
      descricao: this.servicoDescricao,
      quantidade: this.quantidade,
      valorUnit: this.valorNumerico,
      valorTotal: this.quantidade * this.valorNumerico,
      categoria: this.categorias.find(cat => cat.id === +this.categoriaSelecionada) || {} as CategoriaResponse,
      ambiente: this.ambientes.find(amb => amb.id === +this.ambienteSelecionado) || {} as any,
    };
    this.servicosAdicionados.push(novoServico);
    this.calcularTotal();
  }
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }

  removerServico(index: number) {
    this.servicosAdicionados.splice(index, 1);
    this.calcularTotal();
  }

  calcularTotal() {
    this.total = this.servicosAdicionados.reduce((acc, cur) => acc + cur.valorTotal, 0);
  }

  adicionarNovoAmbiente() {
    const novoAmbiente = {
      id: this.ambientes.length + 1,
      descricao: `Ambiente ${this.ambientes.length + 1}`,
    };

  }
 

  removerUltimoAmbiente() {
    this.descricao = '';
    this.data = '';
    this.quantidade = 0;
    this.patrimonio = '';
    this.valorNumerico = 0;
    this.subtotal = 0;
    this.total = 0;
  }

  guardarAmbiente() {
    const ordemData = {
      descricao: this.descricao,
      data: this.data,
      servicos: this.servicosAdicionados,
      total: this.total
    };

    this.ordem.setOrdensServico(ordemData).subscribe({
      next: (response) => {
        console.log('Ordem de serviço cadastrada com sucesso:', response);
        this.closeModal();
      },
      error: (error) => {
        console.error('Erro ao cadastrar ordem de serviço:', error);
      }
    });
  }
  onQuantidadeChange(event: any) {
    const valor = Number(event.target.value);
    this.quantidade = valor >= 0 ? valor : 0;
  }

  onValorChange(event: any) {
    let valorDigitado = event.target.value;
    valorDigitado = valorDigitado.replace(/[^\d,]/g, '');
    valorDigitado = valorDigitado.replace(',', '.');
    const valorNumerico = parseFloat(valorDigitado);

    if (!isNaN(valorNumerico)) {
      this.valor = valorNumerico.toLocaleString('pt-BR', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
      });
    } else {
      this.valor = '';
      this.valorNumerico = 0;
    }
    this.calcularSubtotal();
  }
}

