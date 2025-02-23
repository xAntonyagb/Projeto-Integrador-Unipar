import { AmbienteResponse } from './ambiente.response';
import { CategoriaResponse } from './categoria.response';
import { UsuarioResponse } from './usuario.response';
import { PatrimonioSimpleResponse } from './patrimonio-simple.response';

export interface ServicoResponse {
  id: Number;
  patrimonio: PatrimonioSimpleResponse;
  quantidade: number;
  valorUnit: number;
  valorTotal: number;
  categoria: CategoriaResponse;
  ambiente: AmbienteResponse;
  lastChange: Date;
  lastChangedBy: UsuarioResponse;
}
