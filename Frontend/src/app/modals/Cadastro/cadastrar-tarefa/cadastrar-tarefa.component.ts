import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AddTarefas, getStatusDescricao, StatusTarefa, TarefaResponse} from '../../../dtos/responses/tarefa.response';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { AmbienteRequest } from '../../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { ModalRequest } from '../../../dtos/requests/modal.request';
import { TarefaRequest } from '../../../dtos/requests/tarefa.request';
import * as bootstrap from 'bootstrap';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.scss']
})
export class CadastrarTarefaComponent  implements OnInit {
  isModalOpen = false;
  titulo ='';
  descricao = '';
  previsao = '';
  categoriaSelecionada = '';
  ambienteSelecionado = '';
  statusSelecionado : StatusTarefa | undefined;
  prioridade = '';
  tarefasAdicionadas: AddTarefas[] = [];
  ambientes: any[] = [];
  categorias: CategoriaResponse[] = [];
  statusOptions = Object.values(StatusTarefa).filter(value => typeof value === 'number') as StatusTarefa[];


  constructor(
    private ambiente: AmbienteRequest,
    private categoria : CategoriaRequest,
    private tarefa : TarefaRequest,
    private modalRequest :ModalRequest,
    private toastr: ToastrService,
  ){}
    ngOnInit() {
      this.modalRequest.isOpen$.subscribe( isOpen =>{
      this.isModalOpen = isOpen;
      });
      this.statusOptions = Object.values(StatusTarefa).filter(value => typeof value === 'number') as StatusTarefa[];// Definindo um valor inicial
      console.log('Status Options:', this.statusOptions);
      this.ambientes = [
        {id: 1, descricao: 'Ambiente 1'},
        {id: 2, descricao: 'Ambiente 2'},
        {id: 3, descricao: 'Ambiente 3'},
      ];
      this.loadCategorias();
    }
    loadTarefas() {
      this.tarefa.getTarefa().subscribe((data: AddTarefas[]) => {
        this.tarefasAdicionadas = Array.isArray(data) ? data : [];
      });
    }

  loadCategorias() {
    this.categoria.getCategorias().subscribe((data: CategoriaResponse[]) => {
      console.log('Categorias carregadas:', data);
      this.categorias = Array.isArray(data) ? data : [];
    });
  }

  loadAmbientes() {
    this.ambiente.getAmbientes().subscribe((data: AmbienteResponse[]) => {
      console.log('Ambientes carregados:', data);
      this.ambientes = Array.isArray(data) ? data : [];
    });
  }


  adicionarTarefa() {
      const novaTarefa: AddTarefas = {
        titulo: this.titulo,
        descricao: this.descricao,
        previsao: new Date(this.previsao),
        prioridade: this.prioridade,
        status: this.statusSelecionado as StatusTarefa,
        categoria: this.categorias.find(cat => cat.id === +this.categoriaSelecionada) || {} as CategoriaResponse,
        ambiente: this.ambientes.find(amb => amb.id === +this.ambienteSelecionado) || {} as any,

    };
    this.tarefasAdicionadas.push(novaTarefa);
    this.closeModal();
    this.toastr.success('Tarefa adicionada com sucesso!');
  }
  openModal() {
    this.isModalOpen = true;
    const modalElement = document.getElementById('myModal');
    if (modalElement) {
      modalElement.classList.add('show');
    }
  }

  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.isModalOpen = false;
    this.close.emit();
  }
  protected readonly getStatusDescricao = getStatusDescricao;
}
