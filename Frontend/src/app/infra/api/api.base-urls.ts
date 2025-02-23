export class ApiBaseUrls {
  private static readonly DESENVOLVIMENTO = 'http://localhost:8080';
  private static readonly PRODUCAO = 'http://localhost:8080';

  public static getBaseUrl(): string {
    return this.DESENVOLVIMENTO;
  }
}
