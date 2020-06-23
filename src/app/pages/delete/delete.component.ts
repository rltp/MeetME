import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/service/api.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Ressource } from '../consult/Ressource';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent implements OnInit {
  deleteForm;

  constructor(
    private apiService: ApiService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.deleteForm = this.formBuilder.group({ id: '' });
  }

  ngOnInit(): void { }

  onSubmit(ressource: Ressource) {
    this.apiService.deleteRessource(ressource.id)
      .subscribe(
        data => {
          this.deleteForm.reset();
          this.router.navigate(['/consult']);
        },
        error => {
          alert('Error' + error.error);
          console.log('Error occured', error);
        }
      );
  }
}
