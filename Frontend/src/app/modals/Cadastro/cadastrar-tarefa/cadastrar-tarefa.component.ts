import { Component, EventEmitter, Output } from '@angular/core';
import {AddTarefas, getStatusDescricao, StatusTarefa, TarefaResponse} from '../../../dtos/responses/tarefa.response';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { AmbienteRequest } from '../../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { ModalRequest } from '../../../dtos/requests/modal.request';
import { TarefaRequest } from '../../../dtos/requests/tarefa.request';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.scss']
})
export class CadastrarTarefaComponent {
  isModalOpen = false;
  titulo ='';
  descricao = '';
  previsao = '';
  categoriaSelecionada = '';
  ambienteSelecionado = '';
  statusSelecionado : StatusTarefa | undefined;
  prioridade = '';
  tarefasAdicionadas: AddTarefas[] = [];
  ambientes: AmbienteResponse[] = [];
  categorias: CategoriaResponse[] = [];
  statusOptions = Object.values(StatusTarefa).filter(value => typeof value === 'number') as StatusTarefa[];


  constructor(
    private ambiente: AmbienteRequest,
    private categoria : CategoriaRequest,
    private tarefa : TarefaRequest,
    private modalRequest :ModalRequest,
  ){}
    ngOnInit() {
      this.statusOptions = Object.values(StatusTarefa).filter(value => typeof value === 'number') as StatusTarefa[];// Definindo um valor inicial
      console.log('Status Options:', this.statusOptions);
      this.modalRequest.isOpen$.subscribe( isOpen =>{
      this.isModalOpen = isOpen;
      });
      this.loadTarefas();
      this.loadCategorias();
      this.loadAmbientes();
    }
    loadTarefas() {
      this.tarefa.getTarefa().subscribe((data: any) => {
        this.tarefasAdicionadas = data;
      });
    }

    loadCategorias() {
      this.categoria.getCategorias().subscribe((data: CategoriaResponse[]) => {
      this.categorias = data;
      });
    }
    loadAmbientes() {
      this.ambiente.getAmbientes().subscribe((data: AmbienteResponse[]) => {
      this.ambientes = data;
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
  }
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
  protected readonly getStatusDescricao = getStatusDescricao;
}
