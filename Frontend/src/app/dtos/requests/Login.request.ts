export class LoginRequest {
  username!: string;
  password!: string;

  constructor() {}

  setValues(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}