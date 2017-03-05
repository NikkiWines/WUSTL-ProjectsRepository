<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\Http\Requests;
use DB;
use Auth;
use App\Post;
use App\User;
use App\Comment;


class CommentsController extends Controller
{
    public function store(Request $request, Post $post) {

      $this->validate($request,
      [
        'body' => 'required'
      ]);

      $comment = new Comment;
      $comment->body = $request->body;
      $comment->user_id = Auth::id();
      $post->comments()->save($comment);
      return back();

    }

    public function edit(Comment $comment) {
      $comment->load('user');
      return view('comments.edit', compact('comment'));

    }
    public function update(Request $request, Comment $comment ) {


      $this->validate($request,
      [
        'body' => 'required'
      ]);
      if($comment && ($comment->user_id == $request->user()->id))
      {
        $comment->update($request->all());
      }

      return redirect()->action('PostsController@show', ['id' => $comment->post_id]);
    }
    public function destroy(Request $request, Comment $comment)
    {
      if($comment && ($comment->user_id == $request->user()->id))
      {
        $comment->delete();

      }
      return redirect()->action('PostsController@show', ['id' => $comment->post_id]);

    }
}
