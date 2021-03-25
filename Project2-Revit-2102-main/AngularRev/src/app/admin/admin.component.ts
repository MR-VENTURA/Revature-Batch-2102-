import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../models/account';
import { AccountStatus } from '../models/account-status';
import { AccountRole } from '../models/account-role';
import { AccountService } from '../services/account.service';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
    account: Account;

  constructor(private router: Router, private accountServ: AccountService) { }


  ngOnInit(): void {
    this.getSession();
    this.getBanned();
  }

  getBanned() {
    this.accountServ.getBanned().subscribe(
      account => {
        if(account) {
          this.account = account;
        }
      }
    )
  }

  clickedUnban(account){

      account.accountStatuses.statusId = 1;
      account.accountStatuses.status = "Active";
      console.log("Unban " + account.username);

    this.accountServ.updateAccount(account).subscribe(
      res => {
        //this.post = res;
      }
    );
  }

  getSession() {
    this.accountServ.getSession().subscribe(
      res => {
        if(res) {
          console.log(res, "response");
          this.account = res;
          }
      },
      error => {
        this.router.navigate(['/']);
      }
    )
  }

}
