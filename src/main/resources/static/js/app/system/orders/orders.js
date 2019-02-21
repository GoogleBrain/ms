$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "orders/list",
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
            title: '订单号'
        }, {
            field: 'username',
            title: '用户名'
        }, {
            field: 'deptName',
            title: '总金额'
        },
        {
            field: 'status',
            title: '支付状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">支付成功</span>';
                if (value === '0') return '<span class="badge badge-warning">待支付</span>';
            }
        },
        {
            field: 'status',
            title: '快递状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">暂无</span>';
                if (value === '0') return '<span class="badge badge-warning">派件中</span>';
            }
        },
//        {
//            field: 'email',
//            title: '邮箱'
//        }, {
//            field: 'mobile',
//            title: '手机'
//        }, {
//            field: 'ssex',
//            title: '性别',
//            formatter: function (value, row, index) {
//                if (value === '0') return '男';
//                else if (value === '1') return '女';
//                else return '保密';
//            }
//        }, 
        {
            field: 'crateTime',
            title: '创建时间'
        }, 
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
        $.post(ctx + 'orders/delete', {"ids": ids}, function (r) {
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
    $.post(ctx + "orders/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "orders/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}