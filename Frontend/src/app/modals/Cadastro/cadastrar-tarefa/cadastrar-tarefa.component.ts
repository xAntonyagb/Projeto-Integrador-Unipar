import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { CategoriaResponse } from '../../../dtos/responses/Categoria.response';
import { AmbienteService } from '../../../services/ambiente.service';
import { CategoriaService } from '../../../services/categoria.service';
import { TarefaService } from '../../../services/tarefa.service';
import { ToastrService } from 'ngx-toastr';
import {
  getStatusDescricao,
  StatusTarefa,
} from '../../../dtos/enums/StatusTarefa.enum';
import { TarefaRequest } from '../../../dtos/requests/Tarefa.request';
import { ApiGenericToasts } from '../../../infra/api/api.genericToasts';
import { TarefaResponse } from '../../../dtos/responses/Tarefa.response';

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.scss'],
})
export class CadastrarTarefaComponent implements OnInit {
  isModalOpen = false;
  titulo = '';
  descricao = '';
  previsao = '';
  categoriaSelecionada: string = '';
  ambienteSelecionado = '';
  prioridade: string = '';
  statusSelecionado: string = '';
  tarefasAdicionadas: TarefaResponse[] = [];
  ambientes: AmbienteResponse[] = [];
  categorias: CategoriaResponse[] = [];
  statusArray = Object.entries(StatusTarefa).map(([key, value]) => ({
    key,
    value,
  }));

  constructor(
    private ambiente: AmbienteService,
    private categoria: CategoriaService,
    private tarefa: TarefaService,
    private genericToast: ApiGenericToasts,
  ) {}

  ngOnInit() {
    this.loadAmbientes();
    this.loadCategorias();
  }

  loadTarefas() {
    this.tarefa.getAll(0, 999).subscribe({
      next: (data) => {
        this.tarefasAdicionadas = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  loadCategorias() {
    this.categoria.getAll(0, 999).subscribe({
      next: (data) => {
        this.categorias = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  loadAmbientes() {
    this.ambiente.getAll(0, 999).subscribe({
      next: (data) => {
        this.ambientes = data.content;
      },
      error: (e) => {
        if (e.status === 404) {
          this.ambientes = [];
        } else {
          this.genericToast.showErro(e);
        }
      },
    });
  }

  adicionarTarefa() {
    const novaTarefa: TarefaRequest | any = {
      titulo: this.titulo,
      descricao: this.descricao,
      previsao: new Date(this.previsao),
      prioridade: this.prioridade,
      status: this.statusSelecionado,
      categoria: (
        this.categorias.find((cat) => cat.id === +this.categoriaSelecionada) ||
        ({} as CategoriaResponse)
      ).id,
      ambiente: (
        this.ambientes.find((amb) => amb.id === +this.ambienteSelecionado) ||
        ({} as AmbienteResponse)
      ).id,
    };

    this.tarefa.save(novaTarefa).subscribe({
      complete: () => {
        this.genericToast.showSalvoSucesso(`Tarefa`);
        this.closeModal();
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  openModal() {
    this.isModalOpen = true;
  }
  onSubmit(event: Event): void {}
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
    this.loadTarefas();
  }
  protected readonly getStatusDescricao = getStatusDescricao;
}
