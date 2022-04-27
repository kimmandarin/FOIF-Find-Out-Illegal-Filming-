$("#file_org").on('change',function(){
  var fileName = $("#file_org").val();
  $(".upload-name_org").val(fileName);
});
$("#file_que").on('change',function(){
  var fileName = $("#file_que").val();
  $(".upload-name_que").val(fileName);
});
$("#file_com").on('change',function(){
  var fileName = $("#file_com").val();
  $(".upload-name_com").val(fileName);
});