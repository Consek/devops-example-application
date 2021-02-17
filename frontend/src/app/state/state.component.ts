import {Component, Inject, OnInit} from '@angular/core';
import {Instance, RestService} from "../rest.service";


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


  constructor(private rest: RestService) { }

  ngOnInit(): void {

    this.getInstances();

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
      }
      )
    });
    
  }
}
