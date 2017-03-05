<?php $__env->startSection('content'); ?>
<?php if(!Auth::guest()): ?>
<div class="container">
  <div class="row">
    <div style="color: #6bc3e5; text-align: center">
    <h2>Newsfeed</h2>
  </div>
    <div class="col-md-10 col-md-offset-1">
      <form method="POST" action="/home">
        <div class="form-group">
          <textarea name="body" class="form-control" required></textarea>
          <?php echo e(csrf_field()); ?>

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
        <?php foreach($posts as $post): ?>
        <div>
          <li class="list-group-item">
            <a href="/home/<?php echo e($post->id); ?>"><?php echo e($post->body); ?></a>

            <a href="/profile/<?php echo e($post->user->id); ?>" class="pull-right"><?php echo e($post -> user -> name); ?></a>
            <br>
            <i style="font-size: 10px; color:grey"><?php echo e($post->created_at->format('M d, Y \a\t h:i a')); ?> </i>
            <?php if(!Auth::guest() && ($post->user->id == Auth::user()->id)): ?>
            <a href="/home/<?php echo e($post->id); ?>/edit" class="pull-right" style="color:#263238"><i>Edit</i></a>
            <?php endif; ?>
            <br>
          </li>
          <br>
        </div>
        <?php endforeach; ?>
      </ul>
    </div>
  </div>
</div>
</div>
<?php else: ?>
<div style="text-align: center">
<img src="http://icons.veryicon.com/ico/System/Icons8%20Metro%20Style/System%20Login.ico" alt="login" height="50px" >
<h2 > Please log in to view content </h2>
</div>
<?php endif; ?>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>