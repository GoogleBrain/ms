$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "receive/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                username: $userTableForm.find("input[name='username']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'nickname',
            title: '用户名'
        }, 
        {
            field: 'receiveprovince',
            title: '所在省'
        },
        {
            field: 'receivecity',
            title: '所在市'
        },
        {
            field: 'receivecounty',
            title: '所在县(区)'
        },
        {
            field: 'receivenickname',
            title: '收件人姓名'
        },
        {
            field: 'receivephone',
            title: '收件人手机号'
        },
        {
            field: 'createtime',
            title: '创建时间'
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