$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "frontuser/list",
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
            title: '用户名'
        }, 
//        {
//            field: 'totalrebate',
//            title: '部门'
//        },
//        {
//            field: 'totalamt',
//            title: '邮箱'
//        }, 
        {
            field: 'mobile',
            title: '手机'
        }, {
            field: 'ssex',
            title: '用户类型',
            formatter: function (value, row, index) {
                if (value === '0') return '普通用户';
                else if (value === '1') return '市级代理';
                else if (value === '2') return '省级代理';
                else if (value === '3') return '总代理';
            }
        },
        {
            field: 'crateTime',
            title: '推荐人'
        },
        {
            field: 'crateTime',
            title: '下级人数'
        },
        {
            field: 'crateTime',
            title: '创建时间'
        },
//        {
//            field: 'status',
//            title: '是否已经实名',
//            formatter: function (value, row, index) {
//                if (value === '1') return '<span class="badge badge-success">是</span>';
//                if (value === '0') return '<span class="badge badge-warning">否</span>';
//            }
//        },
        {
            field: 'status',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">有效</span>';
                if (value === '0') return '<span class="badge badge-warning">锁定</span>';
            }
        }

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
        $MB.n_warning('请勾选需要删除的用户！');
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
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'frontuser/delete', {"ids": ids}, function (r) {
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
    $.post(ctx + "frontuser/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "frontuser/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}