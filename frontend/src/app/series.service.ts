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

    async getUserSeriesPendientes(userId: number): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${userId}/series-pendientes`);
        if (!data.ok) {
            throw new Error('Error al obtener las series pendientes');
        }
        return (await data.json()) ?? [];
    }

    async getUserSeriesEmpezadas(userId: number): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${userId}/series-empezadas`);
        if (!data.ok) {
            throw new Error('Error al obtener las series empezadas');
        }
        return (await data.json()) ?? [];
    }

    async getUserSeriesTerminadas(userId: number): Promise<Serie[]> {
        const data = await fetch(`${this.url}/usuarios/${userId}/series-terminadas`);
        if (!data.ok) {
            throw new Error('Error al obtener las series terminadas');
        }
        return (await data.json()) ?? [];
    }

    async getSerieById(id: number): Promise<Serie> {
        const data = await fetch(`${this.url}/series/${id}`);
        if (!data.ok) {
            throw new Error('Error al obtener la serie');
        }
        return (await data.json()) ?? {};
    }

    async getCapitulosBySerieId(serieId: number): Promise<Capitulo[]> {
        const data = await fetch(`${this.url}/series/${serieId}/capitulos`);
        if (!data.ok) {
            throw new Error('Error al obtener los capítulos de la serie');
        }
        return (await data.json()) ?? [];
    }

    async getCapitulosByTemporada(serieId: number, temporada: number): Promise<Capitulo[]> {
        // /{idSerie}/temporada/{idTemporada}
        const data = await fetch(`${this.url}/series/${serieId}/temporada/${temporada}`);
        if (!data.ok) {
            throw new Error('Error al obtener los capítulos de la temporada');
        }
        return (await data.json()) ?? [];

    }

}