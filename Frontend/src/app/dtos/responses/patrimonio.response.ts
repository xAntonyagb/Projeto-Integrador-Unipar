import {AmbienteResponse} from "./ambiente.response";
import {UsuarioResponse} from "./usuario.response";

export interface PatrimonioResponse{
  selected?: boolean;
  id : number;
  descricao: string;
  ambiente: AmbienteResponse;
  lastChange: Date;
  lastChangeBy: UsuarioResponse;
}
