import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../models/account';
import { AccountStatus } from '../models/account-status';
import { AccountRole } from '../models/account-role';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() account: Account;

  constructor(private router: Router, private accountServ: AccountService) { }

  ngOnInit(): void {
    this.getSession();
  }

  getSession() {
    this.accountServ.getSession().subscribe(
      res => {
        if(res) {
          this.account = res;
          // if(this.account.peopleId != null) {
          //     this.router.navigate(['home']);
        }
      },
      error => {
        this.router.navigate(['/']);
      }
    )
  }

  // Account
  clickedClose(){
    console.log("closing " + this.account.username);
    this.account.accountStatuses.statusId = 3;
    this.account.accountStatuses.status = "Closed";

    this.accountServ.updateAccount(this.account).subscribe(
    res => {
      //this.post = res;
      }
    );
  }

}
