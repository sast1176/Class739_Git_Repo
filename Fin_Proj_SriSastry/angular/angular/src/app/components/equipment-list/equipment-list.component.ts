import { Component, OnInit } from '@angular/core';
import { Observer } from 'rxjs/internal/types';
import { EquipmentServiceService } from '../../services/equipment-service.service';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  constructor(private equipmentService: EquipmentServiceService) { }
  equipments: any[] = [];
  ngOnInit(): void {
    this.getEquipments();
    console.log(this.equipments)
  }
  getEquipments() {
    const observer: Observer<any[]> = {
      next: (equipments: any[]) => {
        this.equipments = equipments;
        console.log("testing the code")
      },
      error: (error) => {
        console.log(error);
        // Handle error here, such as displaying an error message to the user
      },
      complete: () => {
        // Handle completion here, if necessary
      }
    };
    this.equipmentService.getAllEquipments().subscribe(observer);
  }
}

