<?php $__env->startSection('content'); ?>
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
          <?php foreach($posts as $post): ?>
          <div>
            <li>
            <!-- <p><?php echo e($post->body); ?></p> -->
            </li>
          </div>
          <?php endforeach; ?>
        </ul>
      </div>
    </div>
  </div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>