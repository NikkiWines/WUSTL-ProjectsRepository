@extends('layouts.app')
@section('content')

<div class="row">
  <div class="col-md-10 col-md-offset-1">
<h1 style="color: #6bc3e5"> Edit Comment </h1>


<form method="POST" action="/home/comments/{{$comment -> id}}">
    {{ method_field('PATCH') }}
  <div class="form-group">
    <textarea name="body" class="form-control" required>{{ $comment -> body}}</textarea>
    {{ csrf_field() }}
  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
    <a href="/home/comments/{{$comment->id}}/delete" class="btn btn-danger">Delete</a>
  </div>
</form>
</div>
</div>
@endsection
