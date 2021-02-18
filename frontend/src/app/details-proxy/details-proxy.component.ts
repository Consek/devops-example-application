import {Component, Input, OnInit} from '@angular/core';
import stc from 'string-to-color';

@Component({
  selector: 'app-details-proxy',
  templateUrl: './details-proxy.component.html',
  styleUrls: ['./details-proxy.component.css']
})
export class DetailsProxyComponent  {

  @Input()
  appName: string;
  @Input()
  isActive: boolean;
  @Input()
  version: string;
  color: string;
  outerColor: string;

  constructor() { }

  ngOnInit(): void {
    const color = stc(this.version);
    this.outerColor = this.isActive? "black": color;
    this.color = color;
  }
}
