@extends('layouts.app')

@section('content')
<div class="col-md-10 col-md-offset-1">

  <div style="text-align:center">
    <h3 style="color: #6bc3e5"> {{ $post->body}} </h3>
    <h5> {{ $post->user -> name}}<h5>
    <h5> {{ $post->updated_at->format('M d, Y \a\t h:i a')}}
  </div>

  <hr>
   <h3 style="color: #6bc3e5"> Add a Comment </h3>
   <form method="POST" action="/home/{{$post-> id}}/comments">
     {{csrf_field()}}
     <div class="form-group">
     <textarea name="body" class="form-control"></textarea>
   </div>
   <div class="form-group" style="text-align: right">
   <button type="submit" class="btn btn-info">Submit</button>
  </div>
   </form>
   <div class="row">
     <div class="col-md-10 col-md-offset-1">
     <ul class="list-group" >
       @foreach ($post->comments as $comment)
       <div>
         <li class="list-group-item">
           {{ $comment->body}}
           <a href="/profile/{{$comment->user -> id}}" class="pull-right">{{ $comment -> user -> name }}</a>
           <br>
           <i style="font-size: 10px; color:grey">{{ $comment->updated_at->format('M d, Y \a\t h:i a')}} </i>
           @if(!Auth::guest() && ($comment->user->id == Auth::user()->id))
           <a href="/home/comments/{{$comment->id}}" class="pull-right" style="color:#263238"><i>Edit</i></a>
           @endif
           <br>
         </li>
         <br>
       </div>
       @endforeach
     </ul>
   </div>
 </div>
 </div>



@endsection
