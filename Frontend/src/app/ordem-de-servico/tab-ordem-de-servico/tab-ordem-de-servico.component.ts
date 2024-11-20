import {Component, OnInit} from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrdemRequest } from '../../dtos/requests/ordem.request';
import { CadastrarOrdemModule } from '../../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module';
import { CadastrarOrdemComponent } from '../../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.component';
import { ModalRequest } from '../../dtos/requests/modal.request';
import {AmbienteRequest} from "../../dtos/requests/ambiente.request";
import {ToastrService} from "ngx-toastr";
import {CategoriaRequest} from "../../dtos/requests/categoria.request";

@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers:[DatePipe]
})
export class TabOrdemDeServicoComponent implements OnInit {
  isModalOpen = false;
  ordensServico: any[] = [];
  ambientesSelecionados: any[] = [];
  categoriasSelecionadas: any[] = [];

  constructor(
    private ordem: OrdemRequest,
    private datePipe: DatePipe,
    private ambiente: AmbienteRequest,
    private toastr: ToastrService,
    private categoria: CategoriaRequest
  ) {}
openModal(){
  this.isModalOpen = true;
  this.loadCategorias();
  this.loadAmbientes();
}
closeModal(){
  this.isModalOpen = false;
}

  ngOnInit() {
    this.getOrdensServico();
  }

  getOrdensServico() {
    this.ordem.getOrdensServico().subscribe(response => {
      this.ordensServico = response.content.map((ordem: any) => ({
        id: ordem.id,
        descricao: ordem.descricao,
        data: this.datePipe.transform(ordem.data, 'dd/MM/yyyy'),
        qtdServicos: ordem.qtdServicos,
        valorTotal: ordem.valorTotal,
        status: ordem.status
      }));
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
}
