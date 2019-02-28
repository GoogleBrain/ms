function updateUser(flag) {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要查看的地址信息！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能查看一个地址信息！');
        return;
    }
    var userId = selected[0].id;
    $.post(ctx + "receive/getReceive", {"userId": userId}, function (r) {
        if (r.code === 0) {
            var $form = $('#user-add');
            var $deptTree = $('#deptTree');
            $form.modal();
            var receiveAddress = r.msg;
            $form.find(".user_password").hide();
            $("#user-add-modal-title").html('收货地址详情');
            $form.find("input[name='nickname']").val(receiveAddress.nickname).attr("readonly", true);
            $form.find("input[name='receiveprovince']").val(receiveAddress.receiveprovince).attr("readonly", true);
            $form.find("input[name='receivecity']").val(receiveAddress.receivecity).attr("readonly", true);
            $form.find("input[name='receivecounty']").val(receiveAddress.receivecounty).attr("readonly", true);
            $form.find("input[name='receivedetailaddress']").val(receiveAddress.receivedetailaddress).attr("readonly", true);
            $form.find("input[name='receivenickname']").val(receiveAddress.receivenickname).attr("readonly", true);
            $form.find("input[name='receivephone']").val(receiveAddress.receivephone).attr("readonly", true);
            $form.find("input[name='createtime']").val(receiveAddress.createtime).attr("readonly", true);
            $("#user-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}