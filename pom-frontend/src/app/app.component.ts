import { Component, OnInit, OnDestroy } from '@angular/core';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { StompService } from 'ng2-stomp-service';

import { RxStompService} from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';



const URL = 'https://149.49.103.28/services/KBRecorder/RecorderService';
const url_socket = "ws://loaclhost:9873/websocket-example"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  sendName: string;
  public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'rec_data'});

  disableConnect: boolean = false;
  disabledDisconnect: boolean = true;
  canSendYourName: boolean = false;

  private subscription : any;

  // constructor(public stompserveice: StompService) {
  //   this.stompserveice.configure({
  //     host: url_socket,
  //     debug: true,
  //     queue: {
  //       "init": false
  //     }
  //   });
  // }
  ngOnInit() {

    this.topicSubscription = this.rxStompService.watch('/topic/user').subscribe((message: Message) => {
      console.log("recieved the message")
      this.receivedMessages.push(message.body);
    });
    // this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    // this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
    //      console.log('ImageUpload:uploaded:', item, status, response);
    //      alert('File uploaded successfully');
    //  };
  }

  // scoketConnect() {
  //   this.stompserveice.startConnect().then( () => {
  //     this.stompserveice.done("init");
  //     console.log("Connect successed");
  //   })
  //   this.stompserveice.subscribe('/topic/user', function(res) {
  //     console.log(JSON.parse(res.body).message);
  //   })
  // }
  // scoketDisconnect() {
  //   if (this.stompserveice) {
  //     this.stompserveice.disconnect();
  //   }
  //   console.log('Disconnected');
  // }


  public receivedMessages: string[] = [];
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) { }



  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage() {
    const message = `Message generated at ${new Date}` + this.sendName;
    console.log(message);
    // this.rxStompService.publish({destination: '/app/user', body: message});
  }

  onDisconnect() {
    console.log("onDisconnect");
  }

  onConnect() {
    console.log("onConnect");
  }


}