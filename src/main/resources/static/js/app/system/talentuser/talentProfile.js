var validator;
var $userProfileForm = $("#update-profile-form");
$(function () {
    $(".profile__img").find("img").attr("src", avatar);
    $("#update-profile").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#default-avatars").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#update-profile .btn-close").click(function () {
        $MB.closeAndRestModal("update-profile");
        validator.resetForm();
        $MB.resetJsTree("deptTree");
    });
    $(".default_avatars_list").find("img").each(function () {
        var $this = $(this);
        $this.on("click", function () {
            var target_src = $(this).attr("src");
            $.post(ctx + "talent/changeAvatar", {"imgName": target_src}, function (r) {
                if (r.code === 0) {
                    $("#close_update_avatar_button").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                    $(".user__img").attr("src", ctx + target_src);
                } else $MB.n_danger(r.msg);
            });
        });
    });
    $(".profile__img__edit").on('click', function () {
        $('#default-avatars').modal();
    });
    validateRule();
    createDeptTree();

    $("#update-profile .btn-save").click(function () {
        getDept();
        var validator = $userProfileForm.validate();
        var flag = validator.form();
        if (flag) {
            $.post(ctx + "talent/updateUserProfile", $userProfileForm.serialize(), function (r) {
                if (r.code === 0) {
                    $("#update-profile .btn-close").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                } else $MB.n_danger(r.msg);
            });
        }
    });

});

function refreshUserProfile() {
    $.post(ctx + "talent/profile", function (r) {
        $main_content.html("").append(r);
    });
}

function editUserProfile() {
    $.post(ctx + "talent/getUserProfile", {"userId": userId}, function (r) {
        if (r.code === 0) {
            var $form = $('#update-profile');
            var $deptTree = $('#deptTree');
            $form.modal();
            var user = r.msg;
            $form.find("input[name='username']").val(user.username).attr("readonly", true);
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='userId']").val(user.userId);
            $form.find("input[name='email']").val(user.email);
            $form.find("input[name='mobile']").val(user.mobile);
            $form.find("input[name='description']").val(user.description);
            $("input:radio[value='" + user.ssex + "']").attr("checked", true);
            $deptTree.jstree().open_all();
            $deptTree.jstree('select_node', user.deptId, true);
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userProfileForm.validate({
        rules: {
            email: {
                email: true
            },
            mobile: {
                checkPhone: true
            },
            ssex: {
                required: true
            },
            description: {
                maxlength: 100
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
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别",
            description: icon + "个人描述不能超过100个字符"
        }
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
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
