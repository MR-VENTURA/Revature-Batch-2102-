import { Component, Input, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { Account } from '../models/Account';
import { AccountRole } from '../models/account-role';
import { AccountStatus } from '../models/account-status';
import { AccountService } from '../services/account.service';
import { PostService } from '../services/post.service';
import { Content } from '../models/content';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() userAccount: Account;
  //Manipulated post
  @Input() post: Post;
  postComments: Post[];
  postDate: string;


  //Check if comment button is clicked.
  isClicked: boolean;

  //Post interactions
  isLiked: boolean;
  isDisliked: boolean;
  isFlagged: boolean;
  isBanned: boolean;

  comment: string;

  //Copy of the original post directly from database.
  originalPost: Post;
  postmsg: string;

  isLoading: boolean;
  isSuccessful: boolean;



  constructor(private accountServ: AccountService, private postService: PostService) {
    this.isClicked = false;
    this.isLiked = false;
    this.isDisliked = false;
    this.postDate = '';
    this.isLoading = false;
    this.isSuccessful = false;
    this.isFlagged = false;
    this.isBanned = false;
  }

  ngOnInit(): void {
    //Copy the original post.
    this.originalPost = JSON.parse(JSON.stringify(this.post));
    this.calcDate();

    this.getComments();
  }

  calcDate() {
    //Alter date into better format.
    let utc: any = this.post.contentId.postDate;
    let d = new Date(0);
    d.setUTCSeconds(utc);
    this.postDate = d.getMonth()+1 + '/' + d.getDate() + '/' + d.getFullYear();
  }

  getComments() {
    this.isLoading = true;
    this.postService.getComments(this.originalPost.postId).subscribe(
      res => {
        this.postComments = res;
        this.isLoading = false;
      },
      err => {
        console.log("no comments");
        this.isLoading = false;
      }
    );
  }

  clickedLike() {
    this.isLiked = !this.isLiked;
    if(this.isLiked) {
      this.originalPost.likes += 1;
      //Check if disliked is true, if so, set it to false.
      if(this.isDisliked)
        this.clickedDislike();
    } else {
      this.originalPost.likes -= 1;
    }
    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  clickedDislike() {
    this.isDisliked = !this.isDisliked;
    if(this.isDisliked) {
      this.originalPost.dislikes += 1;
      //Check if liked is true, if so, set it to false.
      if(this.isLiked)
        this.clickedLike();
    } else {
      this.originalPost.dislikes -= 1;
    }
    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  clickedFlag() {
    this.isFlagged = !this.isFlagged;
    if(this.isFlagged) {
      this.originalPost.flaggedForReview = true;
    }
    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  clickedDelete() {
    this.originalPost.contentId.enabled = false;
    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        this.post.contentId.enabled = false;
        this.onSuccess();
      }
    );
  }

  clickedEdit() {
    let modal = document.getElementById("updateModal");
    modal.style.display = "block";

  }

  clickedUpdate(){
    let modal = document.getElementById("updateModal");
    this.originalPost.postId = this.post.postId;
    this.originalPost.authorId = this.post.authorId;
    this.originalPost.contentId.message = this.postmsg;
    modal.style.display = "none";

    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        //this.post = res;
        this.accountServ.getOnePost(this.originalPost.postId).subscribe(
          res => {
            this.post = res;
          }
        );
        
      }
    );
  }

  clickedCloseUpdate(){
    let modal = document.getElementById("updateModal");
    modal.style.display = "none";
  }

//Admin Fucntionality
  clickedBan() {
    this.isBanned = !this.isBanned;
    if(this.isBanned) {
      this.originalPost.authorId.accountStatuses.statusId = 2;
      this.originalPost.authorId.accountStatuses.status = "Banned";
      console.log("banning " + this.originalPost.authorId.username);
    }
    this.accountServ.updateAccount(this.originalPost.authorId).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  clickedUnflag(){
    this.originalPost.flaggedForReview = false;
    this.postService.updatePost(this.originalPost).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  addComment() {
    this.accountServ.submitPost(this.userAccount, this.comment, "").subscribe(
      res => {
        this.accountServ.getOnePost(res.postId).subscribe(
          resp => {
            resp.parentPostId = this.originalPost;
            this.postService.updatePost(resp).subscribe(
              response => {
                //set comment back to blank.
                this.comment = "";
                this.isClicked = false;
                this.getComments();
                this.calcDate();
                this.onSuccess();
              }
            )
          }
        )
      }
    )
  }

  onSuccess() {
    this.isSuccessful = true;
    setTimeout(() => {
      this.isSuccessful = false;
      document.body.style.overflowY = 'auto';
    }, 1600);
  }
}
