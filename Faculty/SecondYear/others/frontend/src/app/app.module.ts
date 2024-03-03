import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowsePageComponent} from "./pages/browse-page/browse-page.component";
import {DeletePageComponent} from "./pages/delete-page/delete-page.component";
import {AddPageComponent} from "./pages/add-page/add-page.component";
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {ReservationsPageComponent} from "./pages/reservations-page/reservations-page.component";
import {UpdatePageComponent} from "./pages/update-page/update-page.component";



@NgModule({
  declarations: [
    AppComponent,
    AddPageComponent,
    BrowsePageComponent,
    DeletePageComponent,
    HomePageComponent,
    ReservationsPageComponent,
    UpdatePageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
