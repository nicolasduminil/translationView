import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTranslationComponent } from './list-translation.component';

describe('ListTranslationComponent', () => {
  let component: ListTranslationComponent;
  let fixture: ComponentFixture<ListTranslationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTranslationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTranslationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
