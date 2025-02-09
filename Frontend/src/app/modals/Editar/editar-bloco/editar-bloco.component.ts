import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
} from '@angular/core';
import { BlocoResponse } from '../../../dtos/responses/Bloco.response';
import { BlocoService } from '../../../services/bloco.service';
import { MatTableDataSource } from '@angular/material/table';
import { BlocoRequest } from '../../../dtos/requests/Bloco.request';
import { ApiGenericToasts } from '../../../infra/api/api.genericToasts';

@Component({
  selector: 'app-editar-bloco',
  templateUrl: './editar-bloco.component.html',
  styleUrl: './editar-bloco.component.scss',
})
export class EditarBlocoComponent implements OnChanges {
  @Input() blocoParaEditar: BlocoRequest | null = null;
  descricao: string = '';
  dataSource = new MatTableDataSource<BlocoResponse>([]);

  constructor(
    private blocoService: BlocoService,
    private genericToast: ApiGenericToasts,
  ) {}

  @Output() blocoEditado = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  ngOnChanges() {
    if (this.blocoParaEditar) {
      this.descricao = this.blocoParaEditar.descricao;
    }
  }
  loadBlocos() {
    this.blocoService.getAll().subscribe((response: any) => {
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
      let bloco: BlocoRequest = new BlocoRequest();
      bloco.setValues(this.descricao, this.blocoParaEditar.id);

      this.blocoService.save(bloco).subscribe({
        complete: () => {
          this.genericToast.showSalvoSucesso(`Bloco`);
        },
        error: (e) => {
          this.genericToast.showErro(e);
        },
      });

      this.descricao = '';
      this.blocoEditado.emit();
    }
  }

  closeModal() {
    this.close.emit();
  }
}
