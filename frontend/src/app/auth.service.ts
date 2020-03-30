import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { WebRequestService } from './web-request.service';
import { Router } from '@angular/router';
import { shareReplay, tap } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private webService: WebRequestService, private router: Router, private http: HttpClient) { }


  
  signup(name: string, email: string, password: string) {
    return this.webService.signup(name, email, password).pipe(
      shareReplay(),
      tap((res: HttpResponse<any>) => {
        this.setSession(res.body.user._id, res.body.token)
        console.log('signed up')
        this.router.navigate(['/login'])
        //console.log(res)
      })
    )
  }


  login(email: string, password: string) {
    return this.webService.login(email, password).pipe(
      shareReplay(),
      tap((res: HttpResponse<any>) => {
        this.setSession(res.body.user._id, res.body.token)
        console.log('Logged in')
        this.router.navigate(['/lists'])
        //console.log(res)
      })
    )
  }



  logout() {
    this.removeSession()
    this.router.navigate(['/login'])
  }


  getAccessToken() {
    return localStorage.getItem('Authorization')
  }

  
  getUserId() {
    return localStorage.getItem('user-id')
  }

  setAccessToken(accessToken: string) {
    localStorage.setItem('Authorization', accessToken)
  }


  private setSession(userId: string, accessToken: string) {
    localStorage.setItem("user-id", userId)
    localStorage.setItem("Authorization", accessToken)
    
  }

  getNewAccessToken() {
    return this.http.get(`${this.webService.ROOT_URL}/users/me/token`, {
      headers: {
         '_id': this.getUserId()
      }, observe: 'response'
    }).pipe(
      tap((res: HttpResponse<any>) => {
        this.setAccessToken(res.headers.get('Authorization'))
      })
    )
  }

  private removeSession() {
    localStorage.removeItem("user-id")
    localStorage.removeItem("Authorization")
    
  }



}
