import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AmbienteResponse } from '../../../dtos/responses/ambiente.response';
import { PatrimonioService } from '../../../services/patrimonio.service';
import { AmbienteService } from '../../../services/ambiente.service';
import { ToastrService } from 'ngx-toastr';
import { PatrimonioRequest } from '../../../dtos/requests/patrimonio.request';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';

@Component({
  selector: 'app-cadastrar-patrimonio',
  templateUrl: './cadastrar-patrimonio.component.html',
  styleUrl: './cadastrar-patrimonio.component.scss',
})
export class CadastrarPatrimonioComponent implements OnInit {
  isModalOpen = false;
  patrimonio: string = '';
  ambiente: number = 0;
  descricao: string = '';
  ambientesDisponiveis: AmbienteResponse[] = [];

  @Output() patrimonioAdicionado = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  constructor(
    private patrimonioRequest: PatrimonioService,
    private ambienteRequest: AmbienteService,
    private toastr: ToastrService,
    private genericToast: ApiGenericToasts,
  ) {}

  ngOnInit(): void {
    this.loadAmbientes();
  }

  closeModal() {
    this.close.emit();
  }

  loadAmbientes() {
    this.ambienteRequest.getAll(0, 10).subscribe({
      next: (response) => {
        this.ambientesDisponiveis = response.content;
      },
      error: () => {
        this.toastr.error('Erro ao carregar ambientes');
      },
    });
  }

  adicionarPatrimonio() {
    const patrimonioData: PatrimonioRequest | any = {
      patrimonio: this.patrimonio,
      ambiente: this.ambiente || null, // Define ambiente as null if not selected
      descricao: this.descricao,
    };

    this.patrimonioRequest.save(patrimonioData).subscribe({
      next: (response) => {
        this.genericToast.showSalvoSucesso(`Patrimônio`);
        this.patrimonioAdicionado.emit();
        this.closeModal();
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }
}
