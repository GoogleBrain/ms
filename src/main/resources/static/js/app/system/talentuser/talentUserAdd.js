$('.example img').zoomify();
var validator;
var $userAddForm = $("#user-add-form");
var $rolesSelect = $userAddForm.find("select[name='rolesSelect']");
var $roles = $userAddForm.find("input[name='roles']");

$(function () {
    validateRule();
    initRole();
    createDeptTree();

    $("input[name='status']").change(function () {
        var checked = $(this).is(":checked");
        var $status_label = $("#status");
        if (checked) $status_label.html('可用');
        else $status_label.html('禁用');
    });

    $("#user-add .btn-save").click(function () {
        var name = $(this).attr("update");
        alert(name);
        //getDept();
        var validator = $userAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "talent/add", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "talent/update", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#user-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#user-add-button").attr("name", "save");
    validator.resetForm();
    $rolesSelect.multipleSelect('setSelects', []);
    $rolesSelect.multipleSelect("refresh");
    $userAddForm.find("input[name='username']").removeAttr("readonly");
    $userAddForm.find(".user_password").show();
    $userAddForm.find("input[name='status']").prop("checked", true);
    $("#user-add-modal-title").html('新增用户');
    $("#status").html('可用');
    $MB.resetJsTree("deptTree");
    $MB.closeAndRestModal("user-add");

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userAddForm.validate({
        rules: {
            username: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "talent/checkUserName",
                    type: "get",
                    dataType: "json",
                    data: {
                        username: function () {
                            return $("input[name='username']").val().trim();
                        },
                        oldusername: function () {
                            return $("input[name='oldusername']").val().trim();
                        }
                    }
                }
            },
            email: {
                email: true
            },
            roles: {
                required: true
            },
            mobile: {
                checkPhone: true
            },
            ssex: {
                required: true
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            username: {
                required: icon + "请输入用户名",
                minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"
            },
            roles: icon + "请选择用户角色",
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别"
        }
    });
}

function initRole() {
    $.post(ctx + "role/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].roleId + "'>" + data[i].roleName + "</option>"
        }
        $rolesSelect.html("").append(option);
        var options = {
            selectAllText: '所有角色',
            allSelected: '所有角色',
            width: '100%',
            onClose: function () {
                $roles.val($rolesSelect.val());
                validator.element("input[name='roles']");
            }
        };

        $rolesSelect.multipleSelect(options);
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false // 取消多选
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false // 取消选择父节点后选中所有子节点
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })
}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    $("[name='deptId']").val(ref.get_selected()[0]);
}

//点击
var clickImg = function(obj){
    $(obj).parent().find('.upload_input').click();
}
//删除
var deleteImg = function(obj){
    $(obj).parent().find('input').val('');
    $(obj).parent().find('img.preview').attr("src","");
    //IE9以下
    $(obj).parent().find('img.preview').css("filter","");
    $(obj).hide();
    $(obj).parent().find('.addImg').show();
}
//选择图片
function change(file) {
  //预览
  var pic = $(file).parent().find(".preview");
  //添加按钮
  var addImg = $(file).parent().find(".addImg");
  //删除按钮
  var deleteImg = $(file).parent().find(".delete");

  var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();

   // gif在IE浏览器暂时无法显示
   if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
      if (ext != '') {
        alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
      } 
       return;
   }
   //判断IE版本
   var isIE = navigator.userAgent.match(/MSIE/)!= null,
       isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
       isIE10 = navigator.userAgent.match(/MSIE 10.0/)!= null;
   if(isIE && !isIE10) {
      file.select();
      var reallocalpath = document.selection.createRange().text;
       // IE6浏览器设置img的src为本地路径可以直接显示图片
       if (isIE6) {
          pic.attr("src",reallocalpath);
       }else{
          // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现             
          pic.css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")");
          // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
          pic.attr('src','data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==');             
       }
       addImg.hide();
       deleteImg.show();
   }else {
      html5Reader(file,pic,addImg,deleteImg);
   }
}
//H5渲染
function html5Reader(file,pic,addImg,deleteImg){
 var file = file.files[0];
 var reader = new FileReader();
 reader.readAsDataURL(file);
 reader.onload = function(e){
     pic.attr("src",this.result);
 }
 addImg.hide();
 deleteImg.show();
}