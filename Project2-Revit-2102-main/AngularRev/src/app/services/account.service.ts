import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Account } from '../models/account';
import {AccountRole} from '../models/account-role';
import {AccountStatus} from '../models/account-status';
import { Post } from '../models/post';
import { Content } from '../models/content';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  handleLogin(username: string, password: string): Observable<Account> {
    const data = {
      username,
      password
    }
    return this.http.post(`${this.urlServ.getUrl()}user/login`, data, {withCredentials: true}).pipe(
      map(res => res as Account)
    );
  }

  handleLogout(): Observable<any> {
    return this.http.delete(`${this.urlServ.getUrl()}user/`, {withCredentials:true}).pipe();
  }

  getSession(): Observable<Account> {
    return this.http.get(`${this.urlServ.getUrl()}user`, {withCredentials: true}).pipe(
      map(res => res as Account)
    );
  }

  registerAccount(username: string, password: string): Observable<Account>{
    let account = new Account();
    account.peopleId = null;
    account.username = username;
    account.userPass = password;
    let newStatus = new AccountStatus();
    account.accountStatuses = newStatus;
    newStatus.statusId = 1;
    newStatus.status = "Active";
    let newRole = new AccountRole();
    account.accountRoles = newRole;
    newRole.roleId = 1;
    newRole.role = "Author";

    return this.http.post(`${this.urlServ.getUrl()}user`, account, {withCredentials: true}).pipe(
      map(res => res as Account)
    );
  }

  updateAccount(newAccount: Account): Observable<Account> {
    return this.http.put(`${this.urlServ.getUrl()}user/${newAccount.peopleId}`, newAccount, {withCredentials: true}).pipe(
      map(res => res as Account)
    );
  }

  getBanned(): Observable<Account> {
    return this.http.get(`${this.urlServ.getUrl()}user/banned`, {withCredentials: true}).pipe(
      map(res => res as Account)
    );
  }

  //posts
  getPosts(): Observable<Post> {
    return this.http.get(`${this.urlServ.getUrl()}posts`, {withCredentials: true}).pipe(
      map(res => res as Post)
    );
  }

  getOnePost(id: number): Observable<Post> {
    return this.http.get(`${this.urlServ.getUrl()}posts/${id}`, {withCredentials: true}).pipe(
      map(res => res as Post)
    );
  }

  submitPost(account: Account, msg: string, image: string): Observable<Post> {
    let post = new Post();
    post.postId = null;
    post.authorId = account;
    post.parentPostId = null;
    post.flaggedForReview = false;
    post.likes = 0;
    post.dislikes = 0;
    post.lastActivityDate = null;
    let newContent = new Content();
    newContent.message = msg;
    newContent.image = image;
    post.contentId = newContent;

    return this.http.post(`${this.urlServ.getUrl()}posts`, post, {withCredentials: true}).pipe(
      map(res => res as Post)
    );
  }
}
