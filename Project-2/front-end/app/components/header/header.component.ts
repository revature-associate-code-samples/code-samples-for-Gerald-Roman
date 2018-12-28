import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
/*login component*/
import { LoginService } from 'src/app/services/login.service';
import { Observable } from 'rxjs';
import { delay } from 'q';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @ViewChild('cancelBtn') closeBtn: ElementRef;
  public query: string;

  username = '';
  password = '';
  output: string;
  public currentUser: User;
  public error = false;

  constructor(
    public router: Router,
    public loginService: LoginService) {
  }

  ngOnInit() {
  }
  /**
   * user keyed in search and we need to send it to the search results window
   */
  enterPressed() {
    const query: string = this.query.replace(/\s/g, '+');
    this.query = '';
    // change the spaces to plusses and sends it on its way
    this.router.navigateByUrl('/movie_results/' + query);
  }

  /*Login*/
  login() {
    this.error = false;
    this.loginService.validateUser(this.username, this.password).subscribe(
      (user) => {
        this.currentUser = new User(user.id,
                            user.username,
                            user.email,
                            user.password,
                            user.token,
                            user.firstname,
                            user.lastname);
        this.error = false;

        if (this.currentUser == null || this.currentUser === undefined) {
          // console.log('Invalid Credentials' + this.username);
          this.output = 'Invalid Credentials';
        } else {
          this.closeBtn.nativeElement.click();
          this.loginService.persistLogin(this.currentUser);
          this.router.navigateByUrl('/');
        }
      },
      err => {
        this.error = true;
        setTimeout( () => { this.error = false; }, 4000 );
      }
    );
  }

  logout() {
    this.loginService.logout();
  }
}
