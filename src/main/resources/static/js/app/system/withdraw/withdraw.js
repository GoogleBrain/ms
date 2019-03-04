$(function() {
    var $dictTableForm = $(".dict-table-form");
    var settings = {
        url: ctx + "withdraw/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
//                keyy: $dictTableForm.find("input[name='keyy']").val().trim(),
//                valuee: $dictTableForm.find("input[name='valuee']").val().trim(),
//                tableName: $dictTableForm.find("input[name='tableName']").val().trim(),
//                fieldName: $dictTableForm.find("input[name='fieldName']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'frontuserid',
            title: '提现人'
        }, {
            field: 'backuserid',
            title: '操作人'
        }, {
            field: 'withdrawamt',
            title: '提现金额'
        }, 
        {
            field: 'withdrawtime',
            title: '创建时间'
        },
        {
            field: 'withdrawexamstatus',
            title: '审核状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">通过</span>';
                if (value === '2') return '<span class="badge badge-warning">未通过</span>';
                if (value === '3') return '<span class="badge badge-warning">审核中</span>';
            }
        },
        {
            field: 'withdrawstatus',
            title: '提现状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">成功</span>';
                if (value === '2') return '<span class="badge badge-warning">处理中</span>';
            }
        }
        ]
    };

    $MB.initTable('dictTable', settings);
});

function search() {
    $MB.refreshTable('dictTable');
}

function refresh() {
    $(".dict-table-form")[0].reset();
    search();
}

function deleteDicts() {
    var selected = $("#dictTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的字典！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].dictId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的字典？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'withdraw/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDictExcel(){
	$.post(ctx+"withdraw/excel",$(".dict-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportDictCsv(){
	$.post(ctx+"withdraw/csv",$(".dict-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}