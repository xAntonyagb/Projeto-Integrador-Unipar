import { TipoGraficos } from '../../enums/tipo-graficos.enum';

export interface GraficoResponse {
  titulo: string;
  tipo: TipoGraficos;
  dados: Record<string, number>;
}
