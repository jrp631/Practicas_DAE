import {Injectable} from '@angular/core';
import {Serie} from './serie';

const USER_ID: string = '1'; // change if more users are added

@Injectable({
  providedIn: 'root'
})
export class SeriesService
 {
    readonly url = 'http://localhost:8080/usuarios'; // TODO

    constructor() { }

    async getUserSeriesPendientes(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/${USER_ID}/series-pendientes`);
        // print the contenst of the data from the json 
        return (await data.json()) ?? [];
    }

    async getUserSeriesEmpezadas(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/${USER_ID}/series-empezadas`);
        return (await data.json()) ?? [];
    }

    async getUserSeriesTerminadas(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/${USER_ID}/series-terminadas`);
        return (await data.json()) ?? [];
    }
}