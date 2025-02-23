import { Component, EventEmitter, Output } from '@angular/core';
import { UsuarioService } from '../../../services/usuario.service';
import { UsuarioResponse } from '../../../dtos/responses/usuario.response';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms';
import { UsuarioRequest } from '../../../dtos/requests/usuario.request';
import { UsuarioPermissoes } from '../../../dtos/enums/usuario-permissoes.enum';
import { CadastroRequest } from '../../../dtos/requests/cadastro.request';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.scss'],
})
export class CadastrarUsuarioComponent {
  isModalOpen = false;
  username: string = '';
  password: string = '';
  permissoes: UsuarioPermissoes[] = [];
  usuarioResponse: UsuarioResponse[] = [];
  permissoesDisponiveis = Object.values(UsuarioPermissoes);

  constructor(
    private usuario: UsuarioService,
    private toastr: ToastrService,
    private genericToast: ApiGenericToasts,
  ) {
    this.loadUsuarios();
  }

  @Output() usuarioAdicionado = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  closeModal() {
    this.close.emit();
  }

  loadUsuarios() {
    this.usuario.getAll().subscribe({
      next: (data) => {
        this.usuarioResponse = data.content;
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  adicionarUsuario(form: NgForm) {
    console.log('Formulário enviado', form);
    if (!form.valid) {
      console.error('Formulário inválido');
      return;
    }
    const permissoesValidas = this.permissoes.filter((perm) =>
      Object.values(UsuarioPermissoes).includes(perm),
    );
    if (!Array.isArray(this.permissoes)) {
      console.error('Permissões não são um array:', this.permissoes);
      return;
    }
    if (permissoesValidas.length === 0) {
      console.error('Nenhuma permissão válida selecionada');
      return;
    }

    let usuarioData: CadastroRequest = new CadastroRequest();
    usuarioData.setValues(this.username, this.password, permissoesValidas);

    console.log('Dados do usuário:', usuarioData);
    this.usuario.cadastrar(usuarioData).subscribe({
      next: () => {
        console.log('Usuário cadastrado com sucesso!');
        this.usuarioAdicionado.emit();
        this.toastr.success('Usuário cadastrado com sucesso!');
        this.close.emit();
      },
      error: (err) => {
        console.error('Erro ao cadastrar usuário:', err);
        this.toastr.error('Erro ao cadastrar usuário');
      },
    });
  }
}
