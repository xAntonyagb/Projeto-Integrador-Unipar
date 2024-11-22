import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {BlocoRequest} from "../../../dtos/requests/bloco.request";
import {PatrimonioRequest} from "../../../dtos/requests/patrimonio.request";
import {BlocoResponse} from "../../../dtos/responses/bloco.response";
import {PatrimonioResponse} from "../../../dtos/responses/patrimonio.response";
import {AmbienteResponse} from "../../../dtos/responses/ambiente.response";

@Component({
  selector: 'app-cadastrar-ambiente',
  templateUrl: './cadastrar-ambiente.component.html',
  styleUrls: ['./cadastrar-ambiente.component.scss']
})
export class CadastrarAmbienteComponent implements OnInit {
  @Input() blocosSelecionados: BlocoResponse[] = [];
  @Input() patrimoniosDisponiveis: PatrimonioResponse[] = [];
  @Output() ambientesSalvos = new EventEmitter<AmbienteResponse[]>();
  @Output() close = new EventEmitter<void>();
  ambientesAdicionados: any[] = [
    { descricao: '', bloco: { id: null }, patrimonios: [] }
  ];
  isModalOpen = false;
  showPatrimonioOverlay: boolean = false;
  ambienteAtual:any = null;

  constructor(
    private toastr: ToastrService,
    private blocoRequest: BlocoRequest,
    private patrimonioRequest: PatrimonioRequest
  ) {}
  ngOnInit() {
    this.loadBlocos();
    this.loadPatrimonios();
  }
  loadBlocos() {
    this.blocoRequest.getBlocosData().subscribe((response: any) => {
      this.blocosSelecionados = Array.isArray(response) ? response : [];
    });
  }

  loadPatrimonios() {
    this.patrimonioRequest.getPatrimonios(0, 100).subscribe((response: any) => {
      this.patrimoniosDisponiveis = Array.isArray(response.content) ? response.content : [];
    });
  }

  getDescricaoPatrimonios(patrimonios: PatrimonioResponse[]): string {
    return patrimonios.map(p => p.descricao).join(', ');
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
    const idsPatrimoniosSelecionados = this.patrimoniosDisponiveis
      .filter(p => p.selected)
      .map(p => p.id);

    const ambienteParaSalvar = {
      descricao: this.ambienteAtual.nomeAmbiente,
      bloco: this.ambienteAtual.bloco.id,
      patrimonios: idsPatrimoniosSelecionados
    };
    this.closePatrimonioOverlay();
  }

  salvarAmbientes() {
    const ambientesValidados = this.ambientesAdicionados.filter(
      ambiente => ambiente.descricao && ambiente.bloco.id && ambiente.patrimonios.length >= 0
    );

    if (ambientesValidados.length !== this.ambientesAdicionados.length) {
      this.toastr.warning('Por favor, preencha todos os campos antes de salvar!', '',{timeOut:3000});
      return;
    }
    this.ambientesSalvos.emit(ambientesValidados);
    this.closeModal();
    this.toastr.success('Ambientes salvos com sucesso!');

  }

}
