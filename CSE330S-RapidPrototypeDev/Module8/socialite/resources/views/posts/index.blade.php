@extends('layouts.app')
@section('content')
@if(!Auth::guest())
<div class="container">
  <div class="row">
    <div style="color: #6bc3e5; text-align: center">
    <h2>Newsfeed</h2>
  </div>
    <div class="col-md-10 col-md-offset-1">
      <form method="POST" action="/home">
        <div class="form-group">
          <textarea name="body" class="form-control" required></textarea>
          {{ csrf_field() }}
        </div>
        <div class="form-group" style="text-align: right">
          <button type="submit" class="btn btn-info">Post</button>
        </div>
      </form>
    </div>
    <br>
    <div class="row">
      <div class="col-md-10 col-md-offset-1">
      <ul class="list-group" >
        @foreach ($posts as $post)
        <div>
          <li class="list-group-item">
            <a href="/home/{{$post->id}}">{{ $post->body}}</a>

            <a href="/profile/{{$post->user->id}}" class="pull-right">{{ $post -> user -> name }}</a>
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
    </div>
  </div>
</div>
</div>
@else
<div style="text-align: center">
<img src="http://icons.veryicon.com/ico/System/Icons8%20Metro%20Style/System%20Login.ico" alt="login" height="50px" >
<h2 > Please log in to view content </h2>
</div>
@endif
@endsection
