$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "recharge/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                frontuserid: $userTableForm.find("input[name='frontuserid']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'frontuserid',
            title: '用户编号'
        },{
            field: 'rechargeamt',
            title: '充值金额'
        }, {
            field: 'rechargeorderid',
            title: '订单号'
        }, {
            field: 'rechargetime',
            title: '开始时间'
        },{
            field: 'rechargeupdatetime',
            title: '更新时间'
        },{
            field: 'rechargestatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">成功</span>';
                if (value === '2') return '<span class="badge badge-warning">失败</span>';
                if (value === '3') return '<span class="badge badge-warning">处理中</span>';
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
        $MB.n_warning('请勾选需要删除的提现！');
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
        $.post(ctx + 'recharge/delete', {"ids": ids}, function (r) {
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
    $.post(ctx + "recharge/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "recharge/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}