import {TipoGraficos} from "../../enums/TipoGraficos.enum";

export interface GraficoResponse {
  titulo: string;
  tipo: TipoGraficos;
  dados: Record<string, number>;
}
