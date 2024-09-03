import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from "@angular/forms";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy {

  message: string = '';
  level: string = 'info';
  receivedMessages: string[] = [];
  private socket: WebSocket | null = null;

  constructor() {}

  ngOnInit(): void {
    this.connectWebSocket();
  }

  connectWebSocket(): void {
    this.socket = new WebSocket(`ws://localhost:8080/message-sockets/${this.level}`);

    this.socket.onopen = (event) => {
      console.log('WebSocket connection opened', event);
    };

    this.socket.onmessage = (event) => {
      console.log('Message from server:', event.data);
      this.receivedMessages.push(event.data);
    };

    this.socket.onerror = (event) => {
      console.error('WebSocket error observed:', event);
    };

    this.socket.onclose = (event) => {
      console.log('WebSocket connection closed', event);
    };
  }

  send(): void {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      const messagePayload = {
        message: this.message,
        level: this.level,
      };

      this.socket.send(JSON.stringify(messagePayload));  // Senden des JSON-Objekts als String
    } else {
      console.error('WebSocket is not open. Ready state is:', this.socket ? this.socket.readyState : 'Not initialized');
    }
  }

  ngOnDestroy(): void {
    if (this.socket) {
      this.socket.close();
    }
  }

}
