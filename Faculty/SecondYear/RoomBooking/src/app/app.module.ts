import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {AddPageComponent} from "./pages/add-page/add-page.component";
import { AppComponent } from './app.component';
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {HttpClientModule} from "@angular/common/http";
import {DeletePageComponent} from "./pages/delete-page/delete-page.component";
import {ReservationsPageComponent} from "./pages/reservations-page/reservations-page.component";
import {UpdatePageComponent} from "./pages/update-page/update-page.component";
import {BrowsePageComponent} from "./pages/browse-page/browse-page.component";
import { AddPageComponentComponent } from './add-page-component/add-page-component.component';
import {AppRoutingModule} from "./app-routing.module";


@NgModule({
  declarations: [
    AppComponent,
    AddPageComponent,
    BrowsePageComponent,
    DeletePageComponent,
    HomePageComponent,
    ReservationsPageComponent,
    UpdatePageComponent,
    AddPageComponentComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
