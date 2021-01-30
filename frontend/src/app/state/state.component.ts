import { Component, OnInit } from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'state',
  templateUrl: './state.component.html',
  styleUrls: ['./statecomponent.css']
})
export class StateComponent implements OnInit {

  info: string;

  constructor(private rest: RestService) { }

  ngOnInit(): void {

     this.rest.getInstance().subscribe((resp: any) => {
       this.info = resp.hostname;
     });
  }

}
