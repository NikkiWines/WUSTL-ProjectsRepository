<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/
Route::group(['middleware' => ['web']], function () {

  Route::get('/', function () {
    if (!Auth::guest()) {
      return redirect('/home');
    }
      return view('/welcome');
  });

  Route::get('profile', function() { // localhost:8000/about
      return view('profile');
  });

  Route::get('messages', function() { // localhost:8000/about
      return view('messages');
  });


  Route::auth();

  Route::get('/home', 'HomeController@index');

  Route::get('/home', 'PostsController@index');

  Route::get('home/{post}', 'PostsController@show');

  Route::post('home', 'PostsController@store');

  Route::get ('/home/{post}/edit', 'PostsController@edit');

  Route::patch ('/home/{post}', 'PostsController@update');

  Route::get('home/{post}/delete','PostsController@destroy');

  Route::get('/profile/{user}', 'PostsController@userpost');

  Route::patch('/settings/{user}', 'UserController@update');

  Route::get('/messages', 'UserController@message');

  Route::get('/edit', 'UserController@edit');

  Route::patch ('/profile/{user}', 'UserController@update');

  Route::get('/profile/{user}', 'UserController@show');

  Route::post ('home/{post}/comments', 'CommentsController@store');

  Route::get ('home/comments/{comment}', 'CommentsController@edit');

  Route::patch ('home/comments/{comment}', 'CommentsController@update');

  Route::get('home/comments/{comment}/delete','CommentsController@destroy');

  Route::get('password/reset/{token?}', 'Auth\PasswordController@showResetForm');

  Route::post('password/email', 'Auth\PasswordController@sendResetLinkEmail');

  Route::post('password/reset', 'Auth\PasswordController@reset');

  Route::get('messages/sent', 'UserController@email');

});
