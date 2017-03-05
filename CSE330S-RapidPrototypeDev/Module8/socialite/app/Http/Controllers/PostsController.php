<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Requests;
use DB;
use Auth;
use App\Post;
use App\User;
use App\Comment;

class PostsController extends Controller

{
  public function index() {
    $posts= Post::all();
    return view('posts.index', compact('posts'));

  }
  public function show(Post $post) {
    $post->load('user');

    return view('posts.show', compact('post'));

  }
  public function store(Request $request) {

    $this->validate($request,
    [
      'body' => 'required'
    ]);

    $post = new Post;
    $post->body = $request->body;
    $post->user_id = Auth::id();
    $post->save();
    return back();
  }

  public function edit(Post $post) {
    return view('posts.edit', compact('post'));
  }

  public function update(Request $request, Post $post) {

    $this->validate($request,
    [
      'body' => 'required'
    ]);
    if($post && ($post->user_id == $request->user()->id))
    {
      $post->update($request->all());
    }

    return redirect('/home');
  }
  public function destroy(Request $request, Post $post)
  {
    if($post && ($post->user_id == $request->user()->id))
    {
      $post->delete();

    }
    return redirect('/home');
  }
  public function userpost(Post $post) {
    $user= User::where("id", "=", Auth::id())->get();

    $posts= Post::where("user_id", "=", Auth::id())->get();
    return view('profile', compact('posts', 'user'));

  }


}
