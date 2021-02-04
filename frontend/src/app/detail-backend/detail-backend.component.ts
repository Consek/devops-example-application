import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-detail-backend',
  templateUrl: './detail-backend.component.html',
  styleUrls: ['./detail-backend.component.css']
})
export class DetailBackendComponent {

  @Input()
  appName: string;

  @Input()
  start: string;

  @Input()
  end: string;

  constructor() { }

}
