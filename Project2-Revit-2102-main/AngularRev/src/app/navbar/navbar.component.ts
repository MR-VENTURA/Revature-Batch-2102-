import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../models/account';
import { AccountRole } from '../models/account-role';
import { AccountStatus } from '../models/account-status';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Input() account: Account;
  page: string;

  constructor(private accountServ: AccountService, private router: Router) {}

  ngOnInit(): void {
    this.page = window.location.href.substring(21,window.location.href.length);
    console.log(this.page);
  }

  setPage(s: string) {
    this.page = s;
  }

  handleLogout() {
    this.accountServ.handleLogout().subscribe(
      res => {
        this.account.peopleId = null; // Set id == null. Check if id is null. Not logged in.
        this.account.accountRoles = new AccountRole();
        this.account.accountStatuses = new AccountStatus();
        this.account.username = '';
        this.account.userPass = '';
        this.router.navigate(['/']);
      }
    )
  }

  clickAdmin() {
      this.router.navigate(['admin']);
  }

  clickProfile() {
      this.router.navigate(['profile']);
  }

}
