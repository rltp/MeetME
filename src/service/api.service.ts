import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ressource } from 'src/app/pages/consult/Ressource';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  apiURL = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) { }

  public createRessource(ressource: Ressource) {
    const username = 'admin';
    const password = 'root';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password)
      })
    };

    return this.httpClient.post<Ressource>(`${this.apiURL}/users`, ressource, httpOptions)
      .pipe(map(resp => {
      }));
  }

  public updateRessource(ressource: Ressource, id: string) {
    const username = 'admin';
    const password = 'root';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password)
      })
    };

    return this.httpClient.put<Ressource>(`${this.apiURL}/users/` + id, ressource, httpOptions)
      .pipe(map(resp => {
      }));
  }

  public deleteRessource(id: string) {
    const username = 'admin';
    const password = 'root';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password)
      })
    };

    return this.httpClient.delete<Ressource>(`${this.apiURL}/users/` + id, httpOptions)
      .pipe(map(resp => {
      }));
  }

  public getRessourceById(id: string) { }

  public getRessources(url?: string) {
    const username = 'user';
    const password = 'basic';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password)
      })
    };
    return this.httpClient.get<Ressource[]>(`${this.apiURL}/users`, httpOptions);
  }
}
