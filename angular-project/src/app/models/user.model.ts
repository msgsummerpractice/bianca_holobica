export interface User {
  email: string;
  roles: string[];
}

export interface JwtPayload {
  sub: string;
  roles: string[];
  exp: number;
}

export interface SignInResponse {
  token: string;
  email: string;
  roles: string[];
}
