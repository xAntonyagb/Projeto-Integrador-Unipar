import {Injectable} from "@angular/core";
import {AmbienteService} from "../../services/ambiente.service";
import {CategoriaService} from "../../services/categoria.service";
import {OrdemService} from "../../services/ordem.service";
import {Toast, ToastRef, ToastrIconClasses, ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root',
})
export class ApiGenericToasts {

  constructor( private toastr: ToastrService ) { }

  showCarregadosSucesso() {
    this.toastr.success(`Registros carregados com sucesso!` );
  }

  showSalvoSucesso( tipo?: string ) {
    if (tipo) {
      this.toastr.success( `${tipo} salvo com sucesso!` )
    } else {
      this.toastr.success( `Registro salvo com sucesso!`);
    }
  }

  showExcluidoSucesso( tipo?: string ) {
    if (tipo) {
      this.toastr.success( `${tipo} excluído com sucesso!` )
    } else {
      this.toastr.success( `Registro excluído com sucesso!` );
    }
  }

  showErro(response: any ) {
    let listErros: String[] = [];
    try{
      listErros = Object.values(response.error.listErros);
    } catch (e) {
      listErros = [];
    }

    switch ( response.status ) {
      case 500:
        this.toastr.error(
          `Ops! houve um erro interno no servidor. Por favor tente mais tarde.`, '', { timeOut: 4000, progressBar: true } );
      break;

      case 404:
        if (listErros.length > 0) {
          for (let i = 0; i < listErros.length; i++) {
            this.toastr.info( String(listErros[i]), '', { timeOut: 2000, progressBar: true } );
          }
        } else {
          this.toastr.warning( `Nenhum registro foi encontrado.`, '', { timeOut: 2000, progressBar: true } );
        }
        break;

      case 403:
        this.toastr.warning( `Você não tem permissão para realizar essa ação.`, '', { timeOut: 3200, progressBar: true } );
        break;

      case 400:
        if (listErros.length > 0) {
          for (let i = 0; i < listErros.length; i++) {
            this.toastr.error( String(listErros[i]), '', { timeOut: 4000, progressBar: true } );
          }
        } else {
          this.toastr.error( `Erro ao requisitar informações para o servidor. Confira o console para mais detalhes.`, '', { timeOut: 4000, progressBar: true } );
        }
        break;

      default:
        try {
          for (let i = 0; i < listErros.length; i++) {
            this.toastr.warning( String(listErros[i]), '', { timeOut: 4000, progressBar: true } );
          }
        } catch (e) {
          this.toastr.error( `Ops! Um erro inesperado aconteceu. Confira o console para mais detalhes.`, '', { timeOut: 4000, progressBar: true } );
        }
        break;
    }
  }

}
