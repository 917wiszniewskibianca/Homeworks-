import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPageComponentComponent } from './add-page-component.component';

describe('AddPageComponentComponent', () => {
  let component: AddPageComponentComponent;
  let fixture: ComponentFixture<AddPageComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddPageComponentComponent]
    });
    fixture = TestBed.createComponent(AddPageComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
