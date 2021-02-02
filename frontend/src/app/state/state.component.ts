import {Component, Inject, OnInit} from '@angular/core';
import {Instance, RestService} from "../rest.service";
import {DOCUMENT} from "@angular/common";
import 'leader-line';

declare let LeaderLine: any;

@Component({
  selector: 'state',
  templateUrl: './state.component.html',
  styleUrls: ['./state.component.css']
})
export class StateComponent implements OnInit {

  backends = [];

  proxies = [];

  start: string;

  end: string;

  constructor(private rest: RestService, @Inject(DOCUMENT) private document: Document) { }

  ngOnInit(): void {

    this.getInstances();
    this.drawActiveLine();
  }

  getInstances() {

    this.rest.getInstances().subscribe((resp: Instance[]) => {
      resp.forEach(elem => {
        if(elem.isProxy){
          this.proxies.push(elem);
          if(elem.isActive){
            this.start = elem.hostname;
          }
        }else {
          this.backends.push(elem);
          if(elem.isActive){
            this.end = elem.hostname;
          }
        }
      })
    });
    console.log(this.start)
    console.log(this.end)
  }

  refreshData() {
    this.proxies = [];
    this.backends = [];
    this.getInstances();
    this.drawActiveLine();

  }

  drawActiveLine(): void {
    if(this.start && this.end){
      new LeaderLine(document.getElementById(this.start),
        document.getElementById(this.end))
    }
  }
}
