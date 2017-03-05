@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
          <div class="col-xs-3">
          <div class="thumbnail">
           <img src="https://pbs.twimg.com/profile_images/469217937862037504/yd3-lohT.jpeg" alt="...">

           <div class="caption">
             <h3 style="color: #6bc3e5">{{$user->name}}</h3>
             <p>{{$user->email}}</p>
           </div>
         </div>
       </div>
       <div>
         <div class="row">
           <div class="col-md-8">
             @if(!Auth::guest() && ($user->id == Auth::user()->id))
             <form method="POST" action="/home">
               <div class="form-group">
                 <textarea name="body" class="form-control" required></textarea>
                 {{ csrf_field() }}
               </div>
               <div class="form-group" style="text-align: right">
                 <button type="submit" class="btn btn-info">Post</button>
               </div>
             </form>
             <hr>
              @endif
           </div>
         <div class="row">
           <div class="col-md-8">

             <div style="text-align: center">
             <h3 style="color: #6bc3e5"> Posts </h3>
           </div>
           <ul class="list-group" >
             @foreach ($posts as $post)
             <div>
               <li class="list-group-item">
                 <a href="/home/{{$post->id}}">{{ $post->body}}</a>

                 <a href="/profile" class="pull-right">{{ $post -> user -> name }}</a>
                 <br>
                 <i style="font-size: 10px; color:grey">{{ $post->created_at->format('M d, Y \a\t h:i a')}} </i>
                 @if(!Auth::guest() && ($post->user->id == Auth::user()->id))
                 <a href="/home/{{$post->id}}/edit" class="pull-right" style="color:#263238"><i>Edit</i></a>
                 @endif
                 <br>
               </li>
               <br>
             </div>
             @endforeach
           </ul>
           <hr>
           <div style="text-align: center">
           <h3 style="color: #6bc3e5"> Comments </h3>
         </div>
         <ul class="list-group" >
           @foreach ($comments as $comment)
           <div>
             <li class="list-group-item">
               <a href="/home/{{$comment->post_id}}">{{$comment->body}}</a>
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
    </div>
</div>
@endsection
