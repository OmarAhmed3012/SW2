import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';
import { Task } from './models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private webReqService: WebRequestService) { }


  createTask(description: string) {
    // We want to send a web request to create a list
    console.log(this.webReqService.post(`tasks`, { description }))
    return this.webReqService.post(`tasks`, { description });
  }

  getTasks(){
    return this.webReqService.get(`tasks`)
  }

  complete(task: Task) {
    return this.webReqService.patch(`tasks/${task._id}`, {
      completed: !task.completed
    });
}

}
