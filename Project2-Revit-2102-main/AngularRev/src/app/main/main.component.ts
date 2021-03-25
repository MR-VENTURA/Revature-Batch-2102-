import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../models/account';
import { AccountRole } from '../models/account-role';
import { AccountStatus } from '../models/account-status';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  userAccount: Account;

  constructor(private router: Router, private accountServ: AccountService) {
    this.userAccount = new Account();
    this.userAccount.peopleId = null; // Set id == null. Check if id is null. Not logged in.
    this.userAccount.accountRoles = new AccountRole();
    this.userAccount.accountStatuses = new AccountStatus();
    this.userAccount.username = '';
    this.userAccount.userPass = '';

    this.getSession();
  }

  ngOnInit(): void {
  }


  getSession() {
    this.accountServ.getSession().subscribe(
      res => {
        if(res) {
          this.userAccount = res;
        //  if(this.userAccount.peopleId != null)
            //this.router.navigate(['home']);
        }
      },
      error => {
        this.userAccount.peopleId = null; // Set id == null. Check if id is null. Not logged in.
        this.userAccount.accountRoles = new AccountRole();
        this.userAccount.accountStatuses = new AccountStatus();
        this.userAccount.username = '';
        this.userAccount.userPass = '';
      }
    )
  }
}
