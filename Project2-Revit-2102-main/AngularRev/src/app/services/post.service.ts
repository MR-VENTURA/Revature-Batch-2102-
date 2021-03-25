import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post';
import { Account } from '../models/account';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Content } from '../models/content';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  updatePost(newPost: Post): Observable<Post> {
    return this.http.put(`${this.urlServ.getUrl()}posts/${newPost.postId}`, newPost, {withCredentials: true}).pipe(
      map(res => res as Post)
    );
  }

  addComment(account: Account, prior: Post, msg: string): Observable<Post> {
    let post = new Post();
    post.postId = null;
    post.authorId = account;
    post.parentPostId = null;
    post.flaggedForReview = false;
    post.likes = 0;
    post.dislikes = 0;
    let newContent = new Content();
    newContent.enabled = true;
    newContent.message = msg;
    newContent.image = "";
    post.contentId = newContent;

    return this.http.post(`${this.urlServ.getUrl()}posts`, post, {withCredentials: true}).pipe(
      map(res => res as Post)
    );
  }

  getComments(id: number): Observable<Post[]> {
    return this.http.get(`${this.urlServ.getUrl()}posts/comments/${id}`, {withCredentials: true}).pipe(
      map(res => res as Post[])
    );
  }
}
