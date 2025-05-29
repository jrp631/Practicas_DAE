import {Injectable} from '@angular/core';
import {Serie} from './serie';
import {Capitulo} from './capitulo';

const USER_ID: string = '1'; // change if more users are added

@Injectable({
  providedIn: 'root'
})
export class SeriesService
 {
    readonly url = 'http://localhost:8080'; 

    constructor() { }

    async getUserSeriesPendientes(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${USER_ID}/series-pendientes`);
        // print the contenst of the data from the json 
        return (await data.json()) ?? [];
    }

    async getUserSeriesEmpezadas(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${USER_ID}/series-empezadas`);
        return (await data.json()) ?? [];
    }

    async getUserSeriesTerminadas(): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${USER_ID}/series-terminadas`);
        return (await data.json()) ?? [];
    }

    async getSerieById(id: number): Promise<Serie> {
        const data = await fetch(`${this.url}/series/${id}`);
        return (await data.json()) ?? {};
    }

    async getCapitulosBySerieId(serieId: number): Promise<Capitulo[]> {
        const data = await fetch(`${this.url}/series/${serieId}/capitulos`);
        return (await data.json()) ?? [];
    }

    // get capitulos from season

}