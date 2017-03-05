<?php $__env->startSection('content'); ?>
<div class="container">
  <div class="row">
    <div class="col-md-10 col-md-offset-1">
      <div style="text-align: center">
        <h3 style="color: #6bc3e5"> Send Message </h3>
      </div>
      <form method="GET" action="/messages/sent">
        <div class="form-group">
          <h3 style="color: #6bc3e5"> To: </h3>
          <select class="form-control" name="email">
            <?php foreach($users as $user): ?>
            <option name="email" value="<?php echo e($user->email); ?>"><?php echo e($user->name); ?></option>
            <?php endforeach; ?>
          </select>
          <?php echo e(csrf_field()); ?>

          <h3 style="color: #6bc3e5"> Subject: </h3>
          <input name="subject" class="form-control">
          <br>
          <h3 style="color: #6bc3e5"> Body: </h3>
          <textarea name="message" class="form-control"></textarea>
        </div>
        <div class="form-group" style="text-align: right">
          <button type="submit" class="btn btn-info">Send</button>
        </div>
      </div>
    </form>
  </div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>