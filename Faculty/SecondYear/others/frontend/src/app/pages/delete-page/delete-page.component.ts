import {Component,OnInit} from '@angular/core'
import {HttpClient} from "@angular/common/http";
import {GenericService} from "../../generic.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-delete-page',
  templateUrl: './delete-page.component.html',
  styleUrls: ['./delete-page.component.css']
})
export class DeletePageComponent {
  id! : number;

  constructor(private service: GenericService, private router: Router) {
  }

  GetId(value: string) {
    this.id = Number(value);
  }

  onDelete(): void {
    if (this.id) {
      this.service.deleteRoom(this.id).subscribe((response:any) => {
        if (response.success) {
          // Room added successfully, show pop-up notification
          alert('Room deleted successfully!');
        } else {
          // Failed to add room, show error notification
          alert('Failed to delete room. Please try again.');
        }
      });
    }
    else
      alert('Complete the required fields!');
  }
}

