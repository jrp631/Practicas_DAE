import { Injectable } from '@angular/core';
import { Capitulo } from './capitulo';

@Injectable({
    providedIn: 'root'
})
export class CapituloService {
    readonly url = 'http://localhost:8080';

    constructor() {}

    // metodo para ver un capitulo

    // userId, serieId, capituloId

    async putVerCapitulo(userId: String, serieId: String, capituloId: String): Promise<any> {
        // localhost:8080/usuarios/1/series-empezadas/1/1
        console.log(`Marking capitulo ${capituloId} of serie ${serieId} as seen for user ${userId}`);
        const data = await fetch(`${this.url}/usuarios/${userId}/series-empezadas/${serieId}/capitulos/${capituloId}`, {
            method: 'PUT',
        });
        if (!data.ok) {
            throw new Error('Error al marcar el capítulo como visto');
        }
        console.log(data.text);
    }

    // metodo para ver si el capitulo ha sido visto o no
    async getVerCapitulo(userId: String, serieId: String, capituloId: String): Promise<Boolean> {
        //Print the pettition URL
        console.log(`localhost:8080/usuarios/${userId}/series-empezadas/${serieId}/capitulos/${capituloId}`);
        const data = await fetch(`${this.url}/usuarios/${userId}/series-empezadas/${serieId}/capitulos/${capituloId}`);
        if (!data.ok) {
            throw new Error('Error al obtener el estado del capítulo');
        }
        return await data.json() as boolean;
    }


}