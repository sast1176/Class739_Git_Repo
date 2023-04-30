import { Component, OnInit } from '@angular/core';
import { Observer } from 'rxjs/internal/types';
import { UserServiceService} from '../../services/user-service.service';
import { Router } from '@angular/router';
import { UserDto } from 'src/app/dto/user-dto';
import { User } from 'src/app/model/user';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})


export class UserListComponent implements OnInit {
  constructor(private userService : UserServiceService, private router: Router, private toastrService : ToastrService){}
  users: any[] = [];
  userDto : UserDto = new UserDto('','','','');
  responseData : any;
  updateUser = new User('','','','','');
  ngOnInit(): void {
    this.getUsers();
    console.log(this.users)
  }
  getUsers() {
    const observer: Observer<any[]> = {
      next: (users: any[]) => {
        this.users = users;
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
    this.userService.getAllUsers().subscribe(observer);
}
goToUserEquipmentList(userId : string) {
  this.router.navigate(['/user-equipment-list', userId]);
}
onSubmit(userForm : NgForm){
  const data = userForm.value;
  this.userService.saveUser(data).subscribe({
    next: response => {
      console.log(response)
      this.responseData = response;
      this.closeMyModal('#exampleModalCenter');
      this.toastrService.success('User Saved Successfully');
      this.ngOnInit();
      userForm.resetForm();
    },
    error: error => this.toastrService.error(error.error.message)
  });
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
updateUserInfo(id: string, userData: NgForm) {
  this.userService.updateUser(id, userData.value).subscribe({
    next: response => {
      console.log(response)
      this.responseData = response;
      this.toastrService.success("User Updated Successfully");
      this.closeMyModal('#updateModalCenter');
      this.ngOnInit();
    },
    error: error => this.toastrService.error(error.error.message)
  })
}

setData(user : User){
  this.updateUser = user;
}

deleteUser(userId : string){
  this.userService.deleteUser(userId)
    .pipe(
      catchError((error) => {
        console.error('Error deleting user:', error);
        return throwError(() => new Error('test'));
      })
    )
    .subscribe(() => {
      console.log("deleted ");
      this.toastrService.success("User deleted Successfully");
      this.ngOnInit();
    });
}

}
