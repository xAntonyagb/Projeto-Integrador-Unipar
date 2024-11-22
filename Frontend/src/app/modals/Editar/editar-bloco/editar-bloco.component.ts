import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {AddBloco, BlocoResponse} from "../../../dtos/responses/bloco.response";
import {BlocoRequest} from "../../../dtos/requests/bloco.request";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-editar-bloco',
  templateUrl: './editar-bloco.component.html',
  styleUrl: './editar-bloco.component.scss'
})
export class EditarBlocoComponent implements OnChanges {
  @Input() blocoParaEditar: AddBloco | null = null;
  descricao: string = '';
  dataSource = new MatTableDataSource<BlocoResponse>([]);

  constructor(private blocoService: BlocoRequest) {}

  @Output() blocoEditado = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  ngOnChanges() {
    if (this.blocoParaEditar) {
      this.descricao = this.blocoParaEditar.descricao;
    }
  }
  loadBlocos() {
    this.blocoService.getBlocosData().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }
  extractContent(response: any): BlocoResponse[] {
    return response.content || [];
  }
  onSubmit(event: Event): void {
    if (!this.descricao || this.descricao === this.blocoParaEditar?.descricao) {
      return;
    }
    this.editarBloco();
    this.closeModal();
    this.loadBlocos();

  }

  editarBloco() {
    if (this.blocoParaEditar) {
      const blocoEditado: AddBloco = {id:this.blocoParaEditar.id, descricao: this.descricao };
      this.blocoService.updateBloco(this.blocoParaEditar.id, blocoEditado).subscribe(() => {
        this.descricao = '';
        this.blocoEditado.emit();
      });
    }
  }

  closeModal() {
    this.close.emit();
  }
}
