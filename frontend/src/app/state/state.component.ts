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

  }

  getInstances() { 

    var end = '';
    var start = '';

    this.rest.getInstances().subscribe((resp: Instance[]) => {
      resp.forEach(elem => {
        if(elem.isProxy){
          this.proxies.push(elem);
          if(elem.isActive){
            start = elem.hostname;
            if(end){
              new LeaderLine(document.getElementById(start),
                  document.getElementById(end))
            }
          }
        }else {
          this.backends.push(elem);
          if(elem.isActive){
            end = elem.hostname;
            if(start){
              new LeaderLine(document.getElementById(start),
              document.getElementById(end))
            }
          }
        }
      }
     
      
      )
    });
    
  }

  refreshData() {
    this.proxies = [];
    this.backends = [];
    this.getInstances();

  }


}
