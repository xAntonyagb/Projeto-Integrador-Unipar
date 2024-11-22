import {Component, OnInit} from '@angular/core';
import {UsuarioResponse} from "../../dtos/responses/Usuario.response";
import {UsuarioService} from "../../services/usuario.service";
import {EMPTY, Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {ApiGenericToasts} from "../../infra/api/api.genericToasts";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  isModalOpen = false;
  usuarios: UsuarioResponse[] = [];


  constructor(
    private usuarioRequest: UsuarioService,
    private genericToast: ApiGenericToasts
  ) {}

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }
  ngOnInit(): void {
    this.carregarUsuarios()
  }
  carregarUsuarios(): void {
    this.usuarioRequest.getAll().subscribe(
      (usuarios) => {
        console.log('Usuários carregados:', usuarios);
        this.usuarios = usuarios.content;
      },
      (e) => {
        if(e.status === 404) {
          this.usuarios = [];
        } else {
          this.genericToast.showErro(e)
        }
      }
    );
  }
}
