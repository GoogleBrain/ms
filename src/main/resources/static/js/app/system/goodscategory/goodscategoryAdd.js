var validator;
var $deptAddForm = $("#dept-add-form");

$(function () {
    validateRule();
    createDeptTree();

    $("#dept-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getDept();
        validator = $deptAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "goodscategory/add", $deptAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "goodscategory/update", $deptAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#dept-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#dept-add-button").attr("name", "save");
    $("#dept-add-modal-title").html('新增分类');
    validator.resetForm();
    $MB.closeAndRestModal("dept-add");
    $MB.refreshJsTree("deptTree", createDeptTree());
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $deptAddForm.validate({
        rules: {
            deptName: {
                required: true,
                minlength: 2,
                maxlength: 10,
                remote: {
                    url: "goodscategory/checkDeptName",
                    type: "get",
                    dataType: "json",
                    data: {
                        deptName: function () {
                            return $("input[name='deptName']").val().trim();
                        },
                        oldDeptName: function () {
                            return $("input[name='oldDeptName']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            deptName: {
                required: icon + "请输入分类名称",
                minlength: icon + "分类名称长度3到10个字符",
                remote: icon + "该分类名称已经存在"
            }
        }
    });
}

function createDeptTree() {
    $.post(ctx + "goodscategory/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    var deptIds = ref.get_checked();
    $("[name='parentId']").val(deptIds[0]);
}