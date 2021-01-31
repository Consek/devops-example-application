import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-details-proxy',
  templateUrl: './details-proxy.component.html',
  styleUrls: ['./details-proxy.component.css']
})
export class DetailsProxyComponent  {

  @Input()
  appName: string;

  constructor() { }

}
