import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BlocoService } from '../../../services/bloco.service';
import { PatrimonioService } from '../../../services/patrimonio.service';
import { BlocoResponse } from '../../../dtos/responses/Bloco.response';
import { PatrimonioResponse } from '../../../dtos/responses/Patrimonio.response';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { AmbienteService } from '../../../services/ambiente.service';
import { AmbienteRequest } from '../../../dtos/requests/Ambiente.request';
import { ApiGenericToasts } from '../../../infra/api/api.genericToasts';

@Component({
  selector: 'app-cadastrar-ambiente',
  templateUrl: './cadastrar-ambiente.component.html',
  styleUrls: ['./cadastrar-ambiente.component.scss'],
})
export class CadastrarAmbienteComponent implements OnInit {
  @Input() blocosSelecionados: BlocoResponse[] = [];
  @Input() patrimoniosDisponiveis: PatrimonioResponse[] = [];
  @Output() ambientesSalvos = new EventEmitter<AmbienteResponse[]>();
  @Output() close = new EventEmitter<void>();
  ambientesAdicionados: any[] = [
    { descricao: '', bloco: { id: null }, patrimonios: [] },
  ];
  isModalOpen = false;
  showPatrimonioOverlay: boolean = false;
  ambienteAtual: any = null;

  constructor(
    private toastr: ToastrService,
    private blocoRequest: BlocoService,
    private patrimonioRequest: PatrimonioService,
    private ambiente: AmbienteService,
    private genericToast: ApiGenericToasts,
  ) {}

  ngOnInit() {
    this.loadBlocos();
    this.loadPatrimonios();
  }

  loadBlocos() {
    this.blocoRequest.getAll().subscribe((response: any) => {
      this.blocosSelecionados = Array.isArray(response.content)
        ? response.content
        : [];
    });
  }

  loadPatrimonios() {
    this.patrimonioRequest.getAll(0, 999).subscribe({
      next: (data) => {
        this.patrimoniosDisponiveis = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.close.emit();
  }

  removerAmbiente(index: number) {
    this.ambientesAdicionados.splice(index, 1);
  }

  openPatrimonioOverlay(ambiente: any) {
    this.ambienteAtual = ambiente;
    this.showPatrimonioOverlay = true;
  }

  closePatrimonioOverlay() {
    this.showPatrimonioOverlay = false;
  }

  confirmarPatrimonios() {
    const patrimoniosSelecionados = this.patrimoniosDisponiveis.filter(
      (p) => p.selected,
    );
    if (patrimoniosSelecionados.length === 0) {
      this.toastr.warning('Nenhum patrimônio foi selecionado!');
      return;
    }
    if (this.ambienteAtual) {
      this.ambienteAtual.patrimonios = patrimoniosSelecionados.map((p) => ({
        patrimonio: p.patrimonio,
        descricao: p.descricao,
      }));
    }
    this.patrimoniosDisponiveis.forEach((p) => (p.selected = false));
    this.closePatrimonioOverlay();
  }

  salvarAmbientes() {
    const ambiente = this.ambientesAdicionados[0];
    let ambienteRequest: AmbienteRequest = new AmbienteRequest();
    ambienteRequest.setValues(
      ambiente.descricao,
      ambiente.bloco.id,
      ambiente.id,
      ambiente.patrimonios.map((p: any) => p.patrimonio),
    );

    this.ambiente.save(ambienteRequest).subscribe({
      next: (data) => {
        this.ambientesSalvos.emit([data]);
        this.closeModal();
        this.genericToast.showSalvoSucesso(`Ambiente`);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }
}
