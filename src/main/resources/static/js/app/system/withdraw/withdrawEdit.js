function updateDict() {
    var selected = $("#dictTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的字典！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个字典！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "withdraw/getDict", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#dict-add');
            $form.modal();
            var dict = r.msg;
            $("#dict-add-modal-title").html('修改字典');
            $form.find("input[name='frontuserid']").val(dict.frontuserid);
            $form.find("input[name='backuserid']").val(dict.backuserid);
            $form.find("input[name='withdrawamt']").val(dict.withdrawamt);
            $("input:radio[value='" + dict.withdrawexamstatus + "'][name='withdrawexamstatus']").prop("checked", true);
            $("input:radio[value='" + dict.withdrawstatus + "'][name='withdrawstatus']").prop("checked", true);
            $("#dict-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}