import { BlocoResponse } from './Bloco.response';
import { ServicoResponse } from './Servico.response';
import { PatrimonioResponse } from './Patrimonio.response';
import { PatrimonioSimpleResponse } from './PatrimonioSimple.response';

export interface AmbienteResponse {
  id: number;
  descricao: string;
  bloco: BlocoResponse[];
  patrimonios: PatrimonioSimpleResponse[];
  qtdPatrimonios: number;
  lastChange: Date;
}
