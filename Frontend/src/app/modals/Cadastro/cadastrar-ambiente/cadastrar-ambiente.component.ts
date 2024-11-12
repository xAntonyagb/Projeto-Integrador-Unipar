import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-cadastrar-ambiente',
  templateUrl: './cadastrar-ambiente.component.html',
  styleUrls: ['./cadastrar-ambiente.component.scss']
})
export class CadastrarAmbienteComponent {
  ambientesAdicionados: any[] = [];
  nomeAmbiente: string = '';
  bloco: any = {};
  blocosSelecionados: any[] = [];

  @Output() close = new EventEmitter<void>();

  closeModal() {
    this.close.emit();
  }

  removerServico(index: number) {
    this.ambientesAdicionados.splice(index, 1);
  }

  adicionarServico() {
    this.ambientesAdicionados.push({
      nomeAmbiente: this.nomeAmbiente,
      bloco: this.bloco,
      patrimonio: '',
      descricao: ''
    });
  }
}
