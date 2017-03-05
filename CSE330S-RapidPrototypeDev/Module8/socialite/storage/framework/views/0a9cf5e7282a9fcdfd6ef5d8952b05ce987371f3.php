<?php $__env->startSection('content'); ?>
<div class="col-md-10 col-md-offset-1">

  <div style="text-align:center">
    <h3 style="color: #6bc3e5"> <?php echo e($post->body); ?> </h3>
    <h5> <?php echo e($post->user -> name); ?><h5>
    <h5> <?php echo e($post->updated_at->format('M d, Y \a\t h:i a')); ?>

  </div>

  <hr>
   <h3 style="color: #6bc3e5"> Add a Comment </h3>
   <form method="POST" action="/home/<?php echo e($post-> id); ?>/comments">
     <?php echo e(csrf_field()); ?>

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
       <?php foreach($post->comments as $comment): ?>
       <div>
         <li class="list-group-item">
           <?php echo e($comment->body); ?>

           <a href="/profile/<?php echo e($comment->user -> id); ?>" class="pull-right"><?php echo e($comment -> user -> name); ?></a>
           <br>
           <i style="font-size: 10px; color:grey"><?php echo e($comment->updated_at->format('M d, Y \a\t h:i a')); ?> </i>
           <?php if(!Auth::guest() && ($comment->user->id == Auth::user()->id)): ?>
           <a href="/home/comments/<?php echo e($comment->id); ?>" class="pull-right" style="color:#263238"><i>Edit</i></a>
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



<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>