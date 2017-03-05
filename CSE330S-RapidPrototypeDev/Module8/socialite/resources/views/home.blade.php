@extends('layouts.app')
@section('content')
<div class="container">
  <div class="row">
    <div class="col-md-10 col-md-offset-1">
      <form>
        <input class="form-control input-lg" name="body" type="text">
        <br>
        <div style="text-align: center">
          <label class="btn btn-default" type="submit">Post </label>
          <label class="btn btn-default btn-file">
            Attach <input type="file" style="display: none;">
          </label>
        </div>
      </div>
    </form>
      <br>
      <div>
        <ul class="posts">
          @foreach ($posts as $post)
          <div>
            <li>
            <!-- <p>{{ $post->body}}</p> -->
            </li>
          </div>
          @endforeach
        </ul>
      </div>
    </div>
  </div>
</div>
@endsection
