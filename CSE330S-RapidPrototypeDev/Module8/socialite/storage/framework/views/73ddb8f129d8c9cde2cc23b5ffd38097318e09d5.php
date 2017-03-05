<?php $__env->startSection('content'); ?>

<div class="row">
  <div class="col-md-10 col-md-offset-1">
<h1 style="color: #6bc3e5"> Edit Comment </h1>


<form method="POST" action="/home/comments/<?php echo e($comment -> id); ?>">
    <?php echo e(method_field('PATCH')); ?>

  <div class="form-group">
    <textarea name="body" class="form-control" required><?php echo e($comment -> body); ?></textarea>
    <?php echo e(csrf_field()); ?>

  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
    <a href="/home/comments/<?php echo e($comment->id); ?>/delete" class="btn btn-danger">Delete</a>
  </div>
</form>
</div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>