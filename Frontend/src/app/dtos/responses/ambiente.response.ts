import { BlocoResponse } from './bloco.response';
import { ServicoResponse } from './Servico.response';
import { PatrimonioResponse } from './patrimonio.response';
import { PatrimonioSimpleResponse } from './patrimonio-simple.response';

export interface AmbienteResponse {
  id: number;
  descricao: string;
  bloco: BlocoResponse[];
  patrimonios: PatrimonioSimpleResponse[];
  qtdPatrimonios: number;
  lastChange: Date;
}
