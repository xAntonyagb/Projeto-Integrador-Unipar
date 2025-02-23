export enum StatusTarefa {
  ABERTA = 'Pendente',
  ATRASADA = 'Atrasada',
  CONCLUIDA = 'Concluída',
}

export function getStatusDescricao(status: StatusTarefa): string {
  switch (status) {
    case StatusTarefa.ABERTA:
      return 'Aberta';
    case StatusTarefa.ATRASADA:
      return 'Atrasada';
    case StatusTarefa.CONCLUIDA:
      return 'Concluída';
    default:
      return 'Aberta';
  }
}
