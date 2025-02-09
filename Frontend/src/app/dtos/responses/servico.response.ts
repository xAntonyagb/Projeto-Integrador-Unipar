import { AmbienteResponse } from './Ambiente.response';
import { CategoriaResponse } from './Categoria.response';
import { UsuarioResponse } from './Usuario.response';
import { PatrimonioSimpleResponse } from './PatrimonioSimple.response';

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
