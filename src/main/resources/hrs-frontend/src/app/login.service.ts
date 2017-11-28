import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Http, RequestOptions, Headers} from '@angular/http';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {

  isLogged : boolean;

  constructor(private http: Http, private _cookieService:CookieService, private router: Router) { }

  getCookie(key: string){
    return this._cookieService.get(key);
  }

  isLoggedIn(): boolean{
    return this.isLogged;
  }

  public login(username, password): boolean{
    let body = new FormData();
    body.append('username', username);
    body.append('password', password);
    let loginSuccess = false;
    this.http.post('http://localhost:8081/login', body, new RequestOptions({withCredentials: true})).subscribe(
      res => {
        localStorage.setItem('Session', this.getCookie('Session'));
        this.isLogged = true;
        this.router.navigate(['/dashboard']);
        loginSuccess = true;
      },
      err => {
        this.isLogged = false;
        loginSuccess = false;
      }
    );
    return loginSuccess;
  }

  public logout(){
    localStorage.removeItem('Session');
    this.isLogged = false;
    this.router.navigate(['/login']);
  }

}
