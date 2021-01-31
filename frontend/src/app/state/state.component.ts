import {Component, Inject, OnInit} from '@angular/core';
import {RestService, Details} from "../rest.service";
import {WINDOW} from "../windowsProviders";
import {AppDetailsComponent} from "../app-details/app-details.component";


@Component({
  selector: 'state',
  templateUrl: './state.component.html',
  styleUrls: ['./statecomponent.css']
})
export class StateComponent implements OnInit {

  fronts = [];

  sidename = 'front';

  constructor(private rest: RestService, @Inject(WINDOW) private window: Window) { }

  ngOnInit(): void {

     this.rest.postInstance({hostname: 'host', version: '1', id: '15'}).subscribe()

  }

  getInstances() {
    this.rest.getInstances().subscribe((resp: Details[]) => {
      resp.forEach(elem => {
        console.log(elem.hostname);
        this.fronts.push(elem);
      })
    });
  }
}
