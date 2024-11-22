import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AmbienteResponse} from "../../../dtos/responses/ambiente.response";
import {PatrimonioRequest} from "../../../dtos/requests/patrimonio.request";
import {AmbienteRequest} from "../../../dtos/requests/ambiente.request";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-cadastrar-patrimonio',
  templateUrl: './cadastrar-patrimonio.component.html',
  styleUrl: './cadastrar-patrimonio.component.scss'
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
    private patrimonioRequest: PatrimonioRequest,
    private ambienteRequest: AmbienteRequest,
    private toastr: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.loadAmbientes();

  }

  closeModal() {
    this.close.emit();
  }

  loadAmbientes() {
    this.ambienteRequest.getAmbientes(0, 10).subscribe({
      next: (response) => {
        this.ambientesDisponiveis = response.content;
      },
      error: () => {
        this.toastr.error('Erro ao carregar ambientes');
      }
    });
  }

  adicionarPatrimonio() {
    const patrimonioData = {
      patrimonio: this.patrimonio,
      ambiente: this.ambiente || null, // Define ambiente as null if not selected
      descricao: this.descricao
    };
    this.patrimonioRequest.setPatrimonio(patrimonioData).subscribe({
      next: (response) => {
        this.toastr.success('Patrimônio adicionado com sucesso:');
        this.patrimonioAdicionado.emit();
        this.closeModal();
      },
      error: () => {
        this.toastr.error('Erro ao adicionar patrimônio:');
      }
    });
  }
}
