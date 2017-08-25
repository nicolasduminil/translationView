import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTranslationComponent } from './search-translation.component';

describe('SearchTranslationComponent', () => {
  let component: SearchTranslationComponent;
  let fixture: ComponentFixture<SearchTranslationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchTranslationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTranslationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
