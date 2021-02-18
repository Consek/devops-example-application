import {Component, Input, OnInit} from '@angular/core';
import stc from 'string-to-color';

@Component({
  selector: 'app-detail-backend',
  templateUrl: './detail-backend.component.html',
  styleUrls: ['./detail-backend.component.css']
})
export class DetailBackendComponent {

  @Input()
  appName: string;
  @Input()
  isActive: boolean;
  @Input()
  version: string;
  color: string;
  outerColor: string;

  @Input()
  start: string;

  @Input()
  end: string;

  constructor() { }

  ngOnInit(): void {
    const color = stc(this.version);
    this.outerColor = this.isActive? "black": color;
    this.color = color;
  }
}
