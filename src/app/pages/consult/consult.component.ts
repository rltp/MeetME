import { Component, OnInit } from '@angular/core';
import { Ressource } from './Ressource';
import { ApiService } from 'src/service/api.service';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-consult',
  templateUrl: './consult.component.html',
  styleUrls: ['./consult.component.scss']
})
export class ConsultComponent implements OnInit {
  ressources: Array<Ressource>;
  selectedRessource: Ressource;

  constructor(private apiService: ApiService,
    private router: Router) { }

  ngOnInit(): void {
    this.apiService.getRessources().subscribe((res) => {
      this.ressources = res;
      console.log(res)
    });
  }

  onSelect(ressource: Ressource): void {
    this.selectedRessource = ressource;
  }

  deleteRessource(id: string): void {
    this.apiService.deleteRessource(id).subscribe(() => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/consult']);
    })
    this.apiService.getRessources().subscribe((res) => {
      this.ressources = res;
    });
  }

  updateRessource(ressource: Ressource, id: string) {
    this.router.navigate(['/update']);
  }
}
