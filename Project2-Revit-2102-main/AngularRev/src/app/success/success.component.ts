import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

  @Input() show: boolean;
  constructor() {
  }

  ngOnInit(): void {
    document.body.style.overflowY = 'hidden';
  }

}
