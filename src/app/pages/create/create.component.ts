import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/service/api.service';
import { Ressource } from '../consult/Ressource';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';
import { nextTick } from 'q';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {
  ressource: Ressource;
  submitted = false;
  ressourceForm;

  constructor(private apiService: ApiService,
    private formBuilder: FormBuilder,
    private router: Router) {
    this.ressourceForm = this.formBuilder.group({
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
    this.apiService.createRessource(ressource)
      .subscribe(
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
