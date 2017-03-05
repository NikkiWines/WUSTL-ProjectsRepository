@extends('layouts.app')
@section('content')

<div class="row">
  <div class="col-md-10 col-md-offset-1" >
<h1 style="color: #6bc3e5"> User Settings </h1>


<form method="POST" action="/profile/{{Auth::user()->id}}">
    {{ method_field('PATCH') }}
  <div class="form-group">
    <label> Username: </label>
    <input name="name" class="form-control" type="text" value="{{ Auth::user() -> name}}" required>
    {{ csrf_field() }}
  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
  </div>
</form>
<form method="POST" action="/profile/{{Auth::user()->id}}">
    {{ method_field('PATCH') }}
  <div class="form-group">
    <label> Email: </label>
    <input name="email" class="form-control" type="text" value="{{ Auth::user() -> email}}" required>
    {{ csrf_field() }}
  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
  </div>
</form>
</div>
@endsection
