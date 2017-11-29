import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers} from '@angular/http';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from '../../login.service';

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  title = 'Logowanie';
  users = [];
  user: any;
  loggedIn: boolean;
  url = 'http://localhost:8081';
  loginForm: FormGroup;
  session: string;
  constructor(private http: Http, private fb: FormBuilder, private router: Router,
    private _cookieService:CookieService, private loginService: LoginService) {}

  ngOnInit(): void {
    // if (localStorage.getItem('user')) {
    //   this.router.navigate(['/dashboard']);
    // }

    if (localStorage.getItem('Session')) {
      this.router.navigate(['/dashboard']);
    }
    this.createForms();
    // this.getUsers();
    //this.session = this.getCookie("Session");
  }

  // getCookie(key: string){
  //   return this._cookieService.get(key);
  // }

  createForms(): void {
    this.loginForm = this.fb.group({
      email: ['', Validators.compose([Validators.required, Validators.email, Validators.pattern(EMAIL_REGEX)])],
      password: ['', Validators.required]
    });
  }

  getValidErrors(value: FormControl): string {
    return value.errors.required ?
    'Wypełnienie pola jest wymagane!' : value.errors.email ? 'Niepoprawna forma e-mail!' : '';
  }

  onReset(): void {
    this.loginForm.reset();
    this.loggedIn = null;
  }// onReset()

  // getUsers(): void {
  //   this.http.get(this.url + '/users').subscribe(
  //     res => this.users = res.json(),
  //     err => console.log(err)
  //   );
  // }// getUsers()

  // getUser(){
  //   return this.http.get(this.url+"/users/"+this.loginForm.get('email').value).map(
  //     res => {
  //       this.user = res.json();
  //       return localStorage.setItem('user', this.user['id']);
  //     }
  //   );
  // }

  // checkUser(){
  //   // for (let i = 0; i < this.users.length; i++) {
  //   //   if (this.loginForm.get('email').value === this.users[i].email && this.loginForm.get('password').value === this.users[i].password) {
  //   //     return true;
  //   //   }
  //   // }// for
  //   // return false;
  //   let body = new FormData();
  //   body.append('username', this.loginForm.get('email').value);
  //   body.append('password', this.loginForm.get('password').value);
  //   // return this.http.post(this.url+'/login', body).subscribe(
  //   //   res => {
  //   //     this.http.get(this.url+"/users/"+this.loginForm.get('email').value).subscribe(
  //   //       res => {
  //   //         this.user = res.json();
  //   //         localStorage.setItem('user', this.user['id']);
  //   //       }
  //   //     );
  //   //     console.log(this.loggedIn);
  //   //     this.loggedIn = true;
  //   //     //this.router.navigate(['/dashboard']);
  //   //   },
  //   //   //err => this.loggedIn = false,
  //   // );
  //   return this.http.post(this.url+'/login', body, new RequestOptions({withCredentials: true}));

  // }// checkUser()

  onSubmit(){
    this.loggedIn = this.loginService.login(this.loginForm.get('email').value,
      this.loginForm.get('password').value);
    //this.getUser().subscribe();
    // this.checkUser().subscribe(
    //   res => {
    //     this.loggedIn = true;
    //     localStorage.setItem('Session', this.getCookie('Session'));
    //     this.router.navigate(['/dashboard']);
    //   }
    // );
    // return this.loggedIn;
    // if (this.checkUser()) {
    //   localStorage.setItem('user', this.loginForm.value);
    //   this.router.navigate(['/dashboard']);
    //   return this.loggedIn = true;
    // } else {
    //   return this.loggedIn = false;
    // }// if

    // let formObj = this.loginForm.getRawValue();
    // let serializedForm = JSON.stringify(formObj);
    // this.http.post(this.url+'/security/login', serializedForm).subscribe(
    //   res => {
    //     if(res.text()=="false") this.loggedIn = false;
    //     else {
    //       this.http.get(this.url+"/users/"+this.loginForm.get('email').value).subscribe(
    //         res => {
    //           this.user = res.json();
    //           localStorage.setItem('user', this.user['id']);
    //         }
    //       );
    //       this.loggedIn = true;
    //       this.router.navigate(['/dashboard']);
    //     }
    //   }
    // );
  }// onSubmit()
}// AppLoginComponent
