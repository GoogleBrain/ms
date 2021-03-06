$(function() {
    initTreeTable();
});

function initTreeTable() {
    var setting = {
            id: 'id',
            code: 'id',
            url: ctx + 'goodscategory/list',
            expandAll: true,
            expandColumn: "2",
            ajaxParams: {
            	goodscategory: $(".dept-table-form").find("input[name='goodscategory']").val().trim()
            },
            columns: [{
                    field: 'selectItem',
                    checkbox: true
                },
                {
                    title: '编号',
                    field: 'id',
                    width: '50px'
                },
                {
                    title: '名称',
                    field: 'goodscategory'
                },
                {
                    title: '创建时间',
                    field: 'createtime'
                }
            ]
        };

    $MB.initTreeTable('deptTable', setting);
}

function search() {
    initTreeTable();
}

function refresh() {
    $(".dept-table-form")[0].reset();
    search();
    $MB.refreshJsTree("deptTree", createDeptTree());
}

function deleteDepts() {
    var ids = $("#deptTable").bootstrapTreeTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的分类！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中分类？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'goodscategory/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}