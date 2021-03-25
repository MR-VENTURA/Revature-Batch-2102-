import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../models/account';
import { AccountRole } from '../models/account-role';
import { AccountStatus } from '../models/account-status';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Input() account: Account;

  username: string;
  password: string;
  newUser: string;
  newPass: string;

  errorOnLogin: string;

  constructor(private router: Router, private accountServ: AccountService) {
    this.username = '';
    this.password = '';
    this.account = new Account();
    this.account.peopleId = null; // Set id == null. Check if id is null. Not logged in.
    this.account.accountRoles = new AccountRole();
    this.account.accountStatuses = new AccountStatus();
    this.account.username = '';
    this.account.userPass = '';
    this.errorOnLogin = '';
  }

  ngOnInit(): void {
    this.getSession();

  }

  getSession() {
    this.accountServ.getSession().subscribe(
      res => {
        if(res) {
          this.account = res;
          if(this.account.peopleId != null) {
              this.router.navigate(['home']);
          }
        }
      },
      error => {
        this.router.navigate(['/']);
      }
    )
  }

  handleLogin() {
    this.account.username = this.username;
    this.account.userPass = this.password;
    this.accountServ.handleLogin(this.username, this.password).subscribe(
      res => {
        this.account = res;
        this.router.navigate(['home']);
      },
      err => {
        if(err.status == 301) {
          this.errorOnLogin = 'Incorrect password.';
        } else if(err.status == 302) {
          this.errorOnLogin = 'No account with username found.';
        }
      }
    )
  }

  //Register
  clickedRegister() {
    let modal = document.getElementById("registerModal");
    modal.style.display = "block";

  }

  clickedConfirm() {
    let modal = document.getElementById("registerModal");
    this.accountServ.registerAccount(this.newUser, this.newPass).subscribe(
      res => {
        modal.style.display ="none";
        }
    )
  }

  clickedClose(){
    let modal = document.getElementById("registerModal");
    modal.style.display = "none";
  }

}
