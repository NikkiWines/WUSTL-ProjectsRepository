<?php $__env->startSection('content'); ?>

<div class="row">
  <div class="col-md-10 col-md-offset-1" >
<h1 style="color: #6bc3e5"> User Settings </h1>


<form method="POST" action="/profile/<?php echo e(Auth::user()->id); ?>">
    <?php echo e(method_field('PATCH')); ?>

  <div class="form-group">
    <label> Username: </label>
    <input name="name" class="form-control" type="text" value="<?php echo e(Auth::user() -> name); ?>" required>
    <?php echo e(csrf_field()); ?>

  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
  </div>
</form>
<form method="POST" action="/profile/<?php echo e(Auth::user()->id); ?>">
    <?php echo e(method_field('PATCH')); ?>

  <div class="form-group">
    <label> Email: </label>
    <input name="email" class="form-control" type="text" value="<?php echo e(Auth::user() -> email); ?>" required>
    <?php echo e(csrf_field()); ?>

  </div>
  <div class="form-group" style="text-align: right">
    <button type="submit" class="btn btn-info">Update</button>
  </div>
</form>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>