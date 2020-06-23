import { Component, OnInit } from '@angular/core';
import { Ressource } from '../consult/Ressource';
import { ApiService } from 'src/service/api.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {
  ressource: Ressource;
  submitted = false;
  ressourceForm;

  constructor(private apiService: ApiService,
    private formBuilder: FormBuilder,
    private router: Router) {
    this.ressourceForm = this.formBuilder.group({
      id: '',
      name: 'Rodolphe',
      city: 'Paris',
      birthday: formatDate(Date.now(), 'dd/MM/yyyy', 'en-US'),
      description: "Bonjour les filles"
    });
  }

  ngOnInit(): void {

  }

  onSubmit(ressource: Ressource) {
    ressource.birthday = Date.parse(ressource.birthday.toString().split("/").reverse().join("/"));
    this.apiService.updateRessource(ressource, ressource.id).subscribe(
      //success
      data => {
        this.router.navigate(['/consult']);
      },
      // error
      error => {
        alert('Error' + error.error);
        console.log('Error occured', error);
        //afficher message sur IHM
      }
    );
  }

}
