import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HousingLocationComponent } from '../housing-location/housing-location.component';
import { HousingLocation } from '../housinglocation';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-home',
  imports: [ HousingLocationComponent, CommonModule ],
  template: `
    <section>
      <form>
        <input type="text" placeholder="Filter by city" #filter/>
        <!-- filter is a template variable that points to the input element. filter.value is the text in the input element -->
        <button class="primary" type="button" (click)="filterResults(filter.value)">Search</button>
      </form>
    </section>
    <section class="results">
      <!-- Pass the housingLocation object to the child component. The first one is the name of the input in the child component. The second one is the name of the variable in the class below -->
      <!-- <app-housing-location [housingLocation]="housingLocation"></app-housing-location>   -->

      <app-housing-location
        *ngFor="let housingLocation of filteredLocationList"
        [housingLocation]="housingLocation"
      ></app-housing-location>
    </section>
  `,
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  
  housingLocationList: HousingLocation[] = [];  // initialize the list of housing locations, will be populated by the service in the constructor
  housingService: HousingService = inject(HousingService);  // inject the service into the component

  filteredLocationList: HousingLocation[] = [];

  constructor() {
    // this.housingLocationList = this.housingService.getAllHousingLocations();  // get the list of housing locations from the service
    // this.filteredLocationList = this.housingLocationList;  // initialize the filtered list to the full list

    // async call to the service to get the list of housing locations
    this.housingService.getAllHousingLocations().then((housingLocationList: HousingLocation[]) => {
      this.housingLocationList = housingLocationList;
      this.filteredLocationList = housingLocationList;
    });
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.housingLocationList;
      return;
    }
    this.filteredLocationList = this.housingLocationList.filter((housingLocation) =>
      housingLocation?.city.toLowerCase().includes(text.toLowerCase()),
    );
  }

}
