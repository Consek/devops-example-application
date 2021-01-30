import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-app-details',
  templateUrl: './app-details.component.html',
  styleUrls: ['./app-details.component.css']
})
export class AppDetailsComponent implements OnInit {

  @Input()
  appName: string;

  @Input()
  appSide: string
  constructor() { }

  ngOnInit(): void {
  }

}
