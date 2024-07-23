import { Injectable } from '@angular/core';
import { HttpClient,HttpErrorResponse,HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map,throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private url = "http://localhost:4200";
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const url = `${this.url}/api/auth/login`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log('Request URL:', url);
    console.log('Request Headers:', headers);
    console.log('Request Body:', { email, password });    
    return this.http.post<any>(url, { email, password },
      { headers }).pipe(
      catchError(error => {
        console.error('Login error:', error);
        return throwError(error);
      })
    );
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('An error occurred:', error.message);
    return throwError(() => new Error('Something bad happened; please try again later.'));  }
  

  async register(userData:any, token:string):Promise<any>{
    const url = `${this.url}/register`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try{
      const response =  this.http.post<any>(url, userData, {headers}).toPromise()
      return response;
    }catch(error){
      throw error;
    }
  }
  async getAllUsers(token:string):Promise<any>{
    const url = `${this.url}/admin/get-all-users`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try{
      const response =  this.http.get<any>(url, {headers}).toPromise()
      return response;
    }catch(error){
      throw error;
    }
  }

  logOut():void{
    if(typeof localStorage !== 'undefined'){
      localStorage.removeItem('token')
      localStorage.removeItem('role')
    }
  }

  isAuthenticated(): boolean {
    if(typeof localStorage !== 'undefined'){
      const token = localStorage.getItem('token');
      return !!token;
    }
    return false;

  }

  isManager(): boolean {
    if(typeof localStorage !== 'undefined'){
      const role = localStorage.getItem('role');
      return role === 'Manager'
    }
    return false;

  }

  isConsultant(): boolean {
    if(typeof localStorage !== 'undefined'){
      const role = localStorage.getItem('role');
      return role === 'Consultant'
    }
    return false;

  }

  isClient(): boolean {
    if(typeof localStorage !== 'undefined'){
      const role = localStorage.getItem('role');
      return role === 'Client'
    }
    return false;

  }
}
