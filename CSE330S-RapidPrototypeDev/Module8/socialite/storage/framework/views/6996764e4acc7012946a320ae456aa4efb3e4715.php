<?php $__env->startSection('content'); ?>
<div class="container">
  <h3>User Settings</h3>
  <br>
  <label>Change Username</label>
  <br>
  <form method="POST" action="/settings">
    <?php echo e(method_field('PATCH')); ?>

  <input name="name" type="text" style="border-radius:5px" value="<?php echo e(Auth::user() -> name); ?>">

  <div class="form-group">
    <a href=" <?php echo e(url('settings')); ?>" class="btn btn-info">Save</a>
  </div>
</form>

  <br><br>
  <label>Change Password</label>
  <br>
  <input name="password" type="text" style="border-radius:5px">
  <button type="button" class="btn btn-info">Save</button>
  <br><br>
  <form method="POST" action="/settings">
    <label>Add/Change Location</label>
    <br>
    <div class="form-group">
      <input type="text" name="location" class="form-control"><?php echo e(Auth::user() -> location); ?>

    <!-- <input type="text" style="border-radius:5px"> -->
  </div>
    <div class="form-group">
      <button type="submit" class="btn btn-info">Save</button>
    </div>
  </form>
  <br><br>
  <label>Add/Change Birthday</label>
  <div class="container">
    <div class="row">
      <div class='col-sm-6'>
        <div class="form-group">
          <div class='input-group date' id='datetimepicker1'>
            <input type='text' class="form-control" />
            <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </span>
          </div>
        </div>
      </div>
      <!-- <script type="text/javascript">
      $(function () {
        $('#datetimepicker1').datetimepicker();
      });
      </script> -->
    </div>
  </div>

</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', array_except(get_defined_vars(), array('__data', '__path')))->render(); ?>