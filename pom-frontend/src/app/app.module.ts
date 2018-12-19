import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FileSelectDirective } from 'ng2-file-upload';
// import { StompService } from 'ng2-stomp-service';

import { AppComponent } from './app.component';
import { InjectableRxStompConfig, rxStompServiceFactory, RxStompService } from '@stomp/ng2-stompjs';
import { myRxStompConfig } from './my-rx-stomp.config';
import { MessagesComponent } from './messages/messages.component';
import { StatusComponent } from './status/status.component';
import { StompService } from 'ng2-stomp-service';
@NgModule({
  declarations: [
    AppComponent,
    FileSelectDirective,
    MessagesComponent,
    StatusComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    {
    provide: InjectableRxStompConfig,
    useValue: myRxStompConfig
  },
  {
    provide: RxStompService,
    useFactory: rxStompServiceFactory,
    deps: [InjectableRxStompConfig]
  }
  // StompService
],
  bootstrap: [AppComponent]
})
export class AppModule { }
