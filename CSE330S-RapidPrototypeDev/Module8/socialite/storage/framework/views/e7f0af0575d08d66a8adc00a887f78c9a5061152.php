<?php $__env->startSection('content'); ?>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
          <div class="col-xs-3">
          <div class="thumbnail">
           <img src="https://pbs.twimg.com/profile_images/469217937862037504/yd3-lohT.jpeg" alt="...">

           <div class="caption">
             <h3 style="color: #6bc3e5"><?php echo e($user->name); ?></h3>
             <p><?php echo e($user->email); ?></p>
           </div>
         </div>
       </div>
       <div>
         <div class="row">
           <div class="col-md-8">
             <?php if(!Auth::guest() && ($user->id == Auth::user()->id)): ?>
             <form method="POST" action="/home">
               <div class="form-group">
                 <textarea name="body" class="form-control" required></textarea>
                 <?php echo e(csrf_field()); ?>

               </div>
               <div class="form-group" style="text-align: right">
                 <button type="submit" class="btn btn-info">Post</button>
               </div>
             </form>
             <hr>
              <?php endif; ?>
           </div>
         <div class="row">
           <div class="col-md-8">

             <div style="text-align: center">
             <h3 style="color: #6bc3e5"> Posts </h3>
           </div>
           <ul class="list-group" >
             <?php foreach($posts as $post): ?>
             <div>
               <li class="list-group-item">
                 <a href="/home/<?php echo e($post->id); ?>"><?php echo e($post->body); ?></a>

                 <a href="/profile" class="pull-right"><?php echo e($post -> user -> name); ?></a>
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
           <hr>
           <div style="text-align: center">
           <h3 style="color: #6bc3e5"> Comments </h3>
         </div>
         <ul class="list-group" >
           <?php foreach($comments as $comment): ?>
           <div>
             <li class="list-group-item">
               <a href="/home/<?php echo e($comment->post_id); ?>"><?php echo e($comment->body); ?></a>
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
    </div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>