import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { CategoriaResponse } from '../../../dtos/responses/Categoria.response';
import { AmbienteService } from '../../../services/ambiente.service';
import { CategoriaService } from '../../../services/categoria.service';
import { TarefaService } from '../../../services/tarefa.service';
import { getStatusDescricao, StatusTarefa } from '../../../dtos/enums/StatusTarefa.enum';
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
  tituloAtual = '';
  descricaoAtual = '';
  previsaoAtual!: Date;
  categoriaAtual: CategoriaResponse = {} as CategoriaResponse;
  ambienteAtual: AmbienteResponse = {} as AmbienteResponse;
  prioridadeAtual: string = '';
  statusAtual: { key: string; value: string } = { key: '', value: '' };
  lovTarefas: TarefaResponse[] = [];
  lovAmbientes: AmbienteResponse[] = [];
  lovCategorias: CategoriaResponse[] = [];
  lovStatus = Object.entries(StatusTarefa).map(([key, value]) => ({
    key,
    value,
  }));
  showClearCategoria: boolean = false;
  showClearStatus: boolean = false;
  showClearAmbiente: boolean = false;

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
        this.lovTarefas = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  loadCategorias() {
    this.categoria.getAll(0, 999).subscribe({
      next: (data) => {
        this.lovCategorias = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  loadAmbientes() {
    this.ambiente.getAll(0, 999).subscribe({
      next: (data) => {
        this.lovAmbientes = data.content;
      },
      error: (e) => {
        if (e.status === 404) {
          this.lovAmbientes = [];
        } else {
          this.genericToast.showErro(e);
        }
      },
    });
  }

  adicionarTarefa() {
    const novaTarefa: TarefaRequest | any = {
      titulo: this.tituloAtual,
      descricao: this.descricaoAtual,
      previsao: this.previsaoAtual,
      prioridade: this.prioridadeAtual,
      status: this.statusAtual.key,
      categoria: this.categoriaAtual?.id,
      ambiente: this.ambienteAtual?.id,
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

  onCategoriaChange(): void {
    this.showClearCategoria = !(this.categoriaAtual.id === null || this.categoriaAtual.id === undefined);
  }
  onStatusChange(): void {
    this.showClearStatus = this.statusAtual.key.length > 0;
  }
  onAmbienteChange(): void {
    this.showClearAmbiente = !(this.ambienteAtual.id === null || this.ambienteAtual.id === undefined);
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
