import { Component } from '@angular/core';
import { UsersService } from '../users.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
 constructor(private readonly usersService: UsersService,
  private router: Router) {}

  email: string = ''
  password: string = ''
  errorMessage: string = ''

  Submit() {
    if (!this.email || !this.password) {
      this.showError("Email and Password are required");
      return;
    }

    console.log('Attempting to login with email:', this.email);

    this.usersService.login(this.email, this.password).pipe(
      catchError(error => {
        console.error('Login error:', error);
        this.showError(error.message);
        return of(null); 
      })
    ).subscribe(response => {
      if (response) {
        if (response.statusCode === 200) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          this.router.navigate(['/profile']);

        } else {
          var user= response.compte;
          console.log('Error status code:', response.statusCode);
          this.showError(response.message);
        }
      }
    });
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
