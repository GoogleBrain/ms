var validator;
var $dictAddForm = $("#dict-add-form");

$(function () {
    validateRule();

    $("#dict-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $dictAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "base/add", $dictAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "base/update", $dictAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#dict-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#dict-add-button").attr("name", "save");
    $("#dict-add-modal-title").html('新增设置');
    validator.resetForm();
    $MB.closeAndRestModal("dict-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $dictAddForm.validate({
        rules: {
            keyy: {
                required: true,
                maxlength: 40
            },
            valuee: {
                required: true,
                maxlength: 40
            },
            tableName: {
                required: true,
                maxlength: 40
            },
            fieldName: {
                required: true,
                maxlength: 40
            }
        },
        messages: {
            keyy: {
                required: icon + "请输入键名",
                digits: icon + "请输入整数",
                maxlength: icon + "长度不能超过40个字符"
            },
            valuee: {
                required: icon + "请输入键值",
                maxlength: icon + "长度不能超过40个字符"
            },
            tableName: {
                required: icon + "请输入关联表名",
                maxlength: icon + "长度不能超过40个字符"
            },
            fieldName: {
                required: icon + "请输入字段名",
                maxlength: icon + "长度不能超过40个字符"
            }
        }
    });
}