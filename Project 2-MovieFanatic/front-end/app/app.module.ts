import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainViewComponent } from './components/main-view/main-view.component';
import { MovieViewComponent } from './components/movie-view/movie-view.component';
import { ActorViewComponent } from './components/actor-view/actor-view.component';
import { HeaderComponent } from './components/header/header.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { SearchViewComponent } from './components/search-view/search-view.component';
import { MovieReviewComponent } from './components/movie-review/movie-review.component';
import { UserViewComponent } from './components/user-view/user-view.component';
import { PopularMoviesComponent } from './components/popular-movies/popular-movies.component';
import { YearMoviesComponent } from './components/year-movies/year-movies.component';
import { FeaturedMovieComponent } from './components/featured-movie/featured-movie.component';
import { DramaticMoviesComponent } from './components/dramatic-movies/dramatic-movies.component';
import { ComedicMoviesComponent } from './components/comedic-movies/comedic-movies.component';
import { FavoriteMoviesComponent } from './components/favorite-movies/favorite-movies.component';

@NgModule({

  declarations: [
    AppComponent,
    MainViewComponent,
    MovieViewComponent,
    HeaderComponent,
    ProfileViewComponent,
    EditProfileComponent,
    ActorViewComponent,
    MovieReviewComponent,
    SearchViewComponent,
    UserViewComponent,
    PopularMoviesComponent,
    YearMoviesComponent,
    UserViewComponent,
    FeaturedMovieComponent,
    DramaticMoviesComponent,
    ComedicMoviesComponent,
    FavoriteMoviesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
