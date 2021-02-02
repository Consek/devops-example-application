import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class RestService {

  constructor(private http: HttpClient) {}

  getInstances(): Observable<Instance[]> {
    let endpoint = `${environment.backendUrl}/instances`
    return this.http.get<Instance[]>(endpoint).pipe(map(result => <Instance[]>result));
  }
}


export interface Instance {
  hostname: string;
  version: string;
  isProxy: boolean;
  isActive: boolean;
}
