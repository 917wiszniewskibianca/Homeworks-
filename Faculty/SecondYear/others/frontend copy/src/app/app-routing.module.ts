import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {AddPageComponent} from "./pages/add-page/add-page.component";
import {DeletePageComponent} from "./pages/delete-page/delete-page.component";
import {BrowsePageComponent} from "./pages/browse-page/browse-page.component";
import {UpdatePageComponent} from "./pages/update-page/update-page.component";
import {ReservationsPageComponent} from "./pages/reservations-page/reservations-page.component";

const routes: Routes = [
  {path: '',component:HomePageComponent},
  {path: 'app-add-page', component: AddPageComponent},
  {path: 'app-delete-page', component: DeletePageComponent},
  {path: 'app-browse-page', component: BrowsePageComponent},
  {path: 'app-update-page', component: UpdatePageComponent},
  {path: 'app-reservations-page',component: ReservationsPageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
