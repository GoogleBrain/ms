$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "comment/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                username: $userTableForm.find("input[name='username']").val().trim(),
                ssex: $userTableForm.find("select[name='ssex']").val(),
                status: $userTableForm.find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'userId',
            visible: false
        }, {
            field: 'username',
            title: '商品编号'
        }, 
	      {
	      field: 'email',
	      title: '商品标题'
	     }, 
        {
            field: 'realname',
            title: '评论人'
        },
        {
            field: 'comm_tenant_phone',
            title: '评论时间'
        },
//        {
//            field: 'email',
//            title: '邮箱'
//        }, 
//        {
//            field: 'mobile',
//            title: '手机'
//        }, 
//        {
//            field: 'ifrootuser',
//            title: '超级管理员'
//        },
//        {
//            field: 'crateTime',
//            title: '创建时间'
//        }
//        ,
//        {
//            field: 'status',
//            title: '状态',
//            formatter: function (value, row, index) {
//                if (value === '1') return '<span class="badge badge-success">有效</span>';
//                if (value === '0') return '<span class="badge badge-warning">锁定</span>';
//            }
//        }

        ]
    };

    $MB.initTable('userTable', settings);
});

function search() {
    $MB.refreshTable('userTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的评论！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中评论？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'comment/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "comment/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "comment/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}