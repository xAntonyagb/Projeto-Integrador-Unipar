import { AmbienteResponse } from './ambiente.response';
import { UsuarioResponse } from './usuario.response';
import { AmbienteSimpleResponse } from './ambiente-simple.response';

export interface PatrimonioResponse {
  selected?: boolean;
  patrimonio: Number;
  descricao: String;
  ambiente: AmbienteSimpleResponse;
  lastChange: Date;
  lastChangeBy: UsuarioResponse;
}
