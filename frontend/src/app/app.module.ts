import {Inject, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FrontColumnComponent } from './front-column/front-column.component';
import { BackColumnComponent } from './back-column/back-column.component';
import { AppDetailsComponent } from './app-details/app-details.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router'

import { StateComponent } from './state/state.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontColumnComponent,
    BackColumnComponent,
    AppDetailsComponent,
    StateComponent
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
