<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Input;

use App\Http\Requests;
use DB;
use Auth;
use App\Post;
use App\Comment;
use App\User;
use Mail;
class UserController extends Controller
{

  public function message(User $user) {
    $users= User::all();
    return view('messages', compact('users'));

  }
  public function edit(User $user) {
    return view('users.edit', compact('user'));
  }

  public function update(Request $request, User $user) {
    $user= Auth::user();
    $user->update($request->all());

    return redirect()->action('UserController@show', ['id' => Auth::user()->id]);

  }

  public function show(User $user) {
    $posts= Post::where("user_id", "=", $user->id)->get();
    $comments= Comment::where("user_id", "=", $user->id)->get();
    return view('profile', compact('posts', 'user', 'comments'));

  }

  public function email()
   {
       $data1 = Input::all();

       $emailcontent = array (
        'subject' => $data1['subject'],
        'emailmessage' => $data1['message'],
        'email' => $data1['email']
        );


        Mail::send( 'emails.welcome', $emailcontent, function( $message ) use ($data1)
        {
            $message->to($data1['email'])->from( 'socialite@support.com', 'Socialite Support' )->subject($data1['subject']);
        });
        return redirect('/home');
   }



}

?>
