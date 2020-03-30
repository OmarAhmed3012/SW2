import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/task.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Task } from 'src/app/models/task.model';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.scss']
})
export class TaskViewComponent implements OnInit {

  
  tasks: Task[]

  constructor(private taskService: TaskService,private authService: AuthService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        if (this.taskService.getTasks()) {
        this.taskService.getTasks().subscribe((tasks: Task[]) => {
          this.tasks = tasks
        }) }
        else {
          this.tasks = undefined;
        }
      }
    )
   
  }

  onTaskClick(task: Task) {
    //console.log('from task.component.ts')
    this.taskService.complete(task).subscribe(() => {
      console.log('Completed succesfully')
      task.completed = !task.completed
    })
  }

  logout() {
    this.authService.logout()
  }

  

}
