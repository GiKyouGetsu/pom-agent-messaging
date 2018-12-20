import { Component, OnInit, OnDestroy } from '@angular/core';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';

import {map} from 'rxjs/operators';
import {RxStompState} from '@stomp/rx-stomp';

import { RxStompService} from '@stomp/ng2-stompjs';
import { Message, StompHeaders } from '@stomp/stompjs';
import { Subscription, Observable } from 'rxjs';



const URL = 'https://149.49.103.28/services/KBRecorder/RecorderService';
const url_socket = "ws://loaclhost:9873/websocket-example"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  onDisConnected=true;
  onConnected = false;
  sendName: string;
  public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'rec_data'});

  disableConnect: boolean = false;
  disabledDisconnect: boolean = true;
  canSendYourName: boolean = false;

  public connectionStatus$: Observable<string>;
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
  public recieveFeekbakcMessage: string[]  = [];
  private topicSubscription: Subscription;
  private topicSubscriptionFeedback: Subscription;

  constructor(private rxStompService: RxStompService) { 
    this.connectionStatus$ = rxStompService.connectionState$.pipe(map((state) => {
      return RxStompState[state];
    }));
  }


  clearMessage() {
    this.receivedMessages = [];
    this.recieveFeekbakcMessage = [];
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage(sendname: string) {
    const name = `Message generated at ${new Date}=>` + `send name: ` + sendname;
    const param = {
      name: name
    }
    console.log(name);
    this.rxStompService.publish({destination: '/app/action', body: JSON.stringify(param)});
  }

  onDisconnect() {
    this.topicSubscription.unsubscribe();
    this.topicSubscriptionFeedback.unsubscribe();
    this.onConnected = false;
    this.onDisConnected = true;
    console.log("onDisconnect");
  }

  onConnect() {
    console.log("onConnect");
    this.onConnected = true;
    this.onDisConnected = false;
    const headers = {user: "user'"};
    this.topicSubscription = this.rxStompService.watch('/weixingyue' + '/topic/feedback').subscribe((message: Message) => {
      console.log("recieved the message")

      this.receivedMessages.push(JSON.parse(message.body));
    },(err) => {
      console.log("connect falied");
      this.onConnected = true;
      this.onDisConnected = false;  
    });

    this.topicSubscriptionFeedback = this.rxStompService.watch('/topic/feedback').subscribe((message: Message) => {
      console.log("Recieved the feedback massage");
      this.recieveFeekbakcMessage.push(JSON.parse(message.body));
    }, (error => {
      console.log("topic feedback subscribe failed");
    }))
  }
}