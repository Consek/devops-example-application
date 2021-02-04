import {Inject, Component, Input} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import 'leader-line';
import { interval, Subscription} from 'rxjs';

declare let LeaderLine: any;

@Component({
  selector: 'app-line',
  templateUrl: './line.component.html',
  styleUrls: ['./line.component.css']
})
export class LineComponent {

  line: any;

  @Input()
  start: string;

  @Input()
  end: string
  
  mySubscription: Subscription

  constructor(@Inject(DOCUMENT) private document: Document) {
    this.mySubscription= interval(1000).subscribe((x =>{
      this.drawLine();
  }));
   }

  drawLine(): void {
  

    if(document.getElementById(this.start) &&  document.getElementById(this.end)){

      new LeaderLine(document.getElementById(this.start),
      document.getElementById(this.end));
      this.mySubscription.unsubscribe();
    }
  }

}
