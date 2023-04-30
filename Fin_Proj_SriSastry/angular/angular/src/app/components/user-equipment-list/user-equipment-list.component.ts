import { Component, OnInit } from '@angular/core';
import { EquipmentServiceService } from 'src/app/services/equipment-service.service';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms';
import { EquipmentDto } from 'src/app/dto/equipment-dto';
import { Equipment } from 'src/app/model/equipment';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-user-equipment-list',
  templateUrl: './user-equipment-list.component.html',
  styleUrls: ['./user-equipment-list.component.css']
})
export class UserEquipmentListComponent implements OnInit {
  userId: string="";
  responseData: any;
  equipment = new Equipment('','','','',new Date(),'','');
  equipmentList: Equipment[] = [];
  equipmentDto : EquipmentDto = new EquipmentDto('','','',new Date(), '', '');
  updateEquipment = new Equipment('','','','',new Date(),'','');

  constructor(private equipmentService : EquipmentServiceService, private route : ActivatedRoute, private toasterService : ToastrService){}

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id') ?? '' ;
    console.log(this.userId);
    this.equipmentService
    .getEquipmentByUserId(this.userId)
    .subscribe(data => {
      console.log('Equipment:', data);
      this.equipmentList = data;
    });
  }
  onSubmit(form: NgForm) {
    const data = form.value;
    data.userId = this.route.snapshot.paramMap.get('id') ?? '' ;
    this.equipmentService.saveEquipment(data).subscribe({
      next: response => {
        console.log(response)
        this.responseData = response;
        this.closeMyModal('#exampleModalCenter');
        this.toasterService.success('Equipment Saved Successfully');
        this.ngOnInit();
        form.resetForm()
      },
      error: error => this.toasterService.error(error.error.message)
    });
  }
  
  setData(equipment : Equipment){
    this.updateEquipment = equipment;
    console.log(this.updateEquipment);
  }

  updateEquipmentInfo(id: string, equipmentData: NgForm) {
    this.equipmentService.updateEquipment(id, equipmentData.value).subscribe({
      next: response => {
        console.log(response)
        this.responseData = response;
        this.toasterService.success("Equipment Updated Successfully");
        this.closeMyModal('#updateModalCenter');
        this.ngOnInit();
      },
      error: error => this.toasterService.error(error.error.message)
    })
  }

deleteEquipment(equipmentId : string){
  this.equipmentService.deleteEquipment(equipmentId)
    .pipe(
      catchError((error) => {
        console.error('Error deleting equipment:', error);
        return throwError(() => new Error('test'));
      })
    )
    .subscribe(() => {
      console.log("deleted ");
      this.toasterService.success("Equipment deleted Successfully");
      this.callInit();
    });
}

callInit(){
  this.ngOnInit();
}
    closeMyModal(modalName :  string) {
      const modalElement : any = document.querySelector(modalName);
      modalElement.classList.remove('show');
      modalElement.setAttribute('aria-hidden', 'true');
      modalElement.setAttribute('style', 'display: none');
      document.body.classList.remove('modal-open');
      const modalBackdrop : any = document.querySelector('.modal-backdrop');
      modalBackdrop.parentNode.removeChild(modalBackdrop);
    }
  
}
