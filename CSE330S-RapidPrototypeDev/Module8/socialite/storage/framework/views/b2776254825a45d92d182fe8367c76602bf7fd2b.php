<?php $__env->startSection('content'); ?>
<div class="col-md-10 col-md-offset-1">
<h1> Comment</h1>

  <div>
    <?php echo e($comment->body); ?>

  </div>



<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>