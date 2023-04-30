import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { EquipmentListComponent } from './components/equipment-list/equipment-list.component';
import { UserEquipmentListComponent } from './components/user-equipment-list/user-equipment-list.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'user-list', component:UserListComponent },
  { path: 'equipment-list', component:EquipmentListComponent },
  { path: 'user-equipment-list/:id', component:UserEquipmentListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
