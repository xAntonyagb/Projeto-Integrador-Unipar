import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AddTarefas, getStatusDescricao, StatusTarefa, TarefaResponse} from '../../../dtos/responses/tarefa.response';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { AmbienteRequest } from '../../../dtos/requests/ambiente.request';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { ModalRequest } from '../../../dtos/requests/modal.request';
import { TarefaRequest } from '../../../dtos/requests/tarefa.request';
import {ToastrService} from "ngx-toastr";
import {PaginacaoResponse} from "../../../dtos/responses/paginacao.response";

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
  prioridade: string = '';
  statusSelecionado : string= '';
  tarefasAdicionadas: AddTarefas[] = [];
  ambientes: AmbienteResponse[] = [];
  categorias: CategoriaResponse[] = [];
  statusOptions = Object.values(StatusTarefa).filter(value => typeof value === 'number') as StatusTarefa[];
  statusArray = Object.values(this.statusOptions);

  constructor(
    private ambiente: AmbienteRequest,
    private categoria : CategoriaRequest,
    private tarefa : TarefaRequest,
    private toastr: ToastrService,
  ){}

  ngOnInit() {
    this.loadAmbientes();
    this.loadCategorias();
  }

  loadTarefas() {
    const page = 1;
    const size = 10;
    this.tarefa.getTarefa(page, size).subscribe((data: PaginacaoResponse<AddTarefas>) => {
      this.tarefasAdicionadas = data.content;
    });
  }

  loadCategorias() {
    this.categoria.getCategorias().subscribe(data => {
      this.categorias = data
    });
  }

  loadAmbientes() {
    const page = 1;
    const size = 10;
    this.ambiente.getAmbientes(page, size).subscribe((data: PaginacaoResponse<AmbienteResponse>) => {
      console.log('Ambientes carregados:', data);
      this.ambientes = Array.isArray(data.content) ? data.content : [];
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
      ambiente: this.ambientes.find(amb => amb.id === +this.ambienteSelecionado) || {} as AmbienteResponse
    };

    this.tarefa.setTarefa(novaTarefa).subscribe(() => {
      this.toastr.success('Tarefa adicionada com sucesso!');
      this.closeModal();
    });
  }

  openModal() {
    this.isModalOpen = true;
  }
  onSubmit( event: Event): void {

  }
  @Output() close = new EventEmitter<void>();
  closeModal() {

    this.close.emit();
    this.loadTarefas()
  }
  protected readonly getStatusDescricao = getStatusDescricao;
}
