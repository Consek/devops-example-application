import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';

import {StateComponent} from './state/state.component';
import { DetailBackendComponent } from './detail-backend/detail-backend.component';
import { DetailsProxyComponent } from './details-proxy/details-proxy.component';
import { LineComponent } from './line/line.component';

@NgModule({
  declarations: [
    AppComponent,
    StateComponent,
    DetailBackendComponent,
    DetailsProxyComponent,
    LineComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {

  constructor() {
  }
}
