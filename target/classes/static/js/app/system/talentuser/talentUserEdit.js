function updateUser(flag) {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的商户！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个商户！');
        return;
    }
    var userId = selected[0].id;
    $.post(ctx + "talent/getUser", {"id": userId}, function (r) {
        if (r.code === 0) {
            var $form = $('#user-add');
            var $deptTree = $('#deptTree');
            $form.modal();
            var user = r.msg;
            $form.find(".user_password").hide();
            if(2==flag){
            	$("#user-add-modal-title").html('商户详情');
            	$("input").attr("disabled",true);
            	$("#user-add-button").css({ "display": "none" });
            }else if(1==flag){
            	$("input").attr("disabled",false);
            	$("#user-add-button").css({ "display": "inline" });
            	$("#user-add-modal-title").html('修改商户');
            }else if(3==flag){
            	$("#user-add-modal-title").html('商户详情');
            	$("input").attr("disabled",true);
            	$("#user-add-button").css({ "display": "none" });
            }
            $form.find("input[name='talentrealname']").val(user.talentrealname).attr("readonly", true);
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='userId']").val(user.userId);
            $form.find("input[name='email']").val(user.email);
            $form.find("input[name='mobile']").val(user.mobile);
            var roleArr = [];
            for (var i = 0; i < user.roleIds.length; i++) {
                roleArr.push(user.roleIds[i]);
            }
            $form.find("select[name='rolesSelect']").multipleSelect('setSelects', roleArr);
            $form.find("input[name='roles']").val($form.find("select[name='rolesSelect']").val());
            var $status = $form.find("input[name='status']");
            if (user.status === '1') {
                $status.prop("checked", true);
                $status.parent().next().html('可用');
            } else {
                $status.prop("checked", false);
                $status.parent().next().html('禁用');
            }
            $("input:radio[value='" + user.ssex + "']").prop("checked", true);
            $deptTree.jstree().open_all();
            $deptTree.jstree('select_node', user.deptId, true);
            $("#user-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}