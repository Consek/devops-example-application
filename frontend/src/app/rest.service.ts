import {Injectable} from '@angular/core';
import {catchError} from 'rxjs/internal/operators';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class RestService {

  constructor(private http: HttpClient) {}

  getInstances(): Observable<Details[]> {
    let endpoint = 'http://localhost:8080/instances'
    return this.http.get<Details[]>(endpoint).pipe(map(result => <Details[]>result));
  }

  postInstance(details: Details): Observable<any> {
    let endpoint = 'http://localhost:8080/instances';
    return this.http.post(endpoint, details).pipe(
     catchError(this.handleError)
    )
  }



  private handleError(error: HttpErrorResponse): any {
      if (error.error instanceof ErrorEvent) {
        console.error('An error occurred:', error.error.message);
      } else {
        console.error(
          `Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }
      return throwError(
        'Something bad happened; please try again later.');
    }

  private extractData(res: Response): any {
    const body = res;
    return body || { };
  }

}


export interface Details {
  id: string;
  hostname: string;
  version: string;
}
