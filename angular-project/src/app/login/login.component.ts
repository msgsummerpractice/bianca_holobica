import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  NonNullableFormBuilder,
  FormControl,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

type LoginForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  private readonly _fb = inject(NonNullableFormBuilder);
  private readonly _authService = inject(AuthService);
  private readonly _router = inject(Router);

  hidePassword = true;
  step: 'LOGIN' | 'MFA' = 'LOGIN';
  mfaCodeControl = new FormControl('', [Validators.required]);
  userEmail = '';
  errorMessage = '';

  protected readonly loginForm: FormGroup<LoginForm> = this._fb.group<LoginForm>({
    email: this._fb.control('', [Validators.required, Validators.email]),
    password: this._fb.control('', [Validators.required, Validators.minLength(6)]),
  });

  protected get authService(): AuthService {
    return this._authService;
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.errorMessage = '';
      const credentials = this.loginForm.getRawValue();

      this._authService.login(credentials).subscribe({
        next: (res) => {
          this.userEmail = res.email;
          this.step = 'MFA';
        },
        error: (err) => {
          this.errorMessage = 'Email or password is incorrect!';
        },
      });
    }
  }

  onVerifyMfa(): void {
    if (this.mfaCodeControl.valid && this.mfaCodeControl.value) {
      this.errorMessage = '';

      this._authService
        .verifyMfa({ email: this.userEmail, code: this.mfaCodeControl.value })
        .subscribe({
          next: (res) => {
            this._authService.saveToken(res.token);
            this._router.navigate(['/breeds']);
          },
          error: (err) => {
            this.errorMessage = 'The MFA code is invalid or has expired!';
          },
        });
    }
  }

  onLogout(): void {
    this._authService.logout();
  }
}
