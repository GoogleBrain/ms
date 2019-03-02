function updateDept() {
    var selected = $("#deptTable").bootstrapTreeTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的分类！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个分类！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "goodscategory/getDept", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#dept-add');
            var $deptTree = $('#deptTree');
            $form.modal();
            var dept = r.msg;
            $("#dept-add-modal-title").html('修改商品分类');
            $form.find("input[name='goodscategory']").val(dept.goodscategory);
            $form.find("input[name='id']").val(dept.id);
            $deptTree.jstree('select_node', dept.parentId, true);
            $deptTree.jstree('disable_node', dept.id);
            $("#dept-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}