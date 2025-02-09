import { AmbienteResponse } from './Ambiente.response';
import { UsuarioResponse } from './Usuario.response';
import { AmbienteSimpleResponse } from './AmbienteSimple.response';

export interface PatrimonioResponse {
  selected?: boolean;
  patrimonio: Number;
  descricao: String;
  ambiente: AmbienteSimpleResponse;
  lastChange: Date;
  lastChangeBy: UsuarioResponse;
}
