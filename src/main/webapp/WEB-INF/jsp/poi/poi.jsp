<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>人事管理系统</title>
    <!-- jq -->
    <script src="../js/jquery-3.1.1.min.js"></script>

    <!-- bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.min.js"></script>

    <!-- 分页插件 -->
    <link href="../css/bootstrap-table.min.css" rel="stylesheet">
    <script src="../js/bootstrap-table.js"></script>
    <script src="../js/bootstrap-table-locale-all.min.js"></script>

    <!--layer -->
    <link href="../js/layer/theme/default/layer.css" rel="stylesheet">

    <link href="../css/font-awesome/css/font-awesome.min.css" rel="stylesheet">


    <link href="../css/animate.css" rel="stylesheet">

    <style type="text/css">
        .panel {
            margin-left: -48px;
            width: 1145px;
        }

        .col-sm-12 {
            margin-left: -60px;
        }

        thead {
            background: #428bca;
            color: white;
        }
    </style>
</head>
<body>
<div class="container" style="margin-top:5px">
    <div class="row">
        <!--!查询区 -->
        <div class="panel panel-default">
            <div class="panel-heading" style="background-color:#428bca;color: white">
                查询条件
            </div>
            <div class="panel-body form-group" style="margin-bottom:0px;">
                <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">用户名：</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="uame" id="search_name"/>
                </div>
                   <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">状态：</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="status" id="search_status"/>
                </div>
                <div class="col-sm-1 col-sm-offset-4">
                    <button class="btn btn-primary" class="search" type="button" id="search_btn"
                            style="background-color: #0767C8 ">生成报表
                    </button>
                </div>
            </div>
        </div>

        <div id="toolbar" class="btn-group">
            <button id="exportExcel" type="button" class="btn btn-success">
                <i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;导出EXCEL
            </button>
            <%--<button id="btn_edit" type="button" class="btn btn-primary" >--%>
            <%--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改--%>
            <%--</button>--%>
            <button id="exportPDF" type="button" class="btn btn-warning" style="margin-left: 10px">
                <i class="fa fa-file-pdf-o" aria-hidden="true"></i>&nbsp;导出PDF
            </button>
        </div>
        <!-- 表格 -->
        <div class="col-sm-12">
            <table class="table table-striped table-bordered table-hover" id="table"></table>
        </div>
    </div>
</div>
<script src="../js/layer/layer.js"></script>
<script type="text/javascript">


    //导出excel操作
    $("#exportExcel").click(function () {
        window.location.href = "${ctx}/poi/createExcel?username="+$("#search_name").val();
    })

    //获取选中的所有行的id
    function getIds() {
        return $.map($("#table").bootstrapTable('getSelections'), function (row) {
            return row.id;
        })
    }

    class BstpTable {
        constructor(obj) {
            this.obj = obj;
        }

        inint() {
            //---先销毁表格 ---
            this.obj.bootstrapTable('destroy');
            //---初始化表格,动态从服务器加载数据---
            this.obj.bootstrapTable({
                //【发出请求的基础信息】
                url: '${ctx}/userJson1',
                method: 'post',
                contentType: "application/x-www-form-urlencoded",//必须有
                //【查询设置】
                /* queryParamsType的默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                                  设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber */
                queryParamsType: '',
                queryParams: function queryParams(params) {
                    //自定义传递的参数
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        username: $("#search_name").val(),
                       status:$("#search_status").val()
                    };
                    return param;
                },

                //【其它设置】
                locale: 'zh-CN',//中文支持
                pagination: true,//是否开启分页（*）
                striped: true,
                pageNumber: 1,//初始化加载第一页，默认第一页
                pageSize: 5,//每页的记录行数（*）
                pageList: [5, 10, 15],//可供选择的每页的行数（*）
                sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
                showRefresh: true,//刷新按钮
                showToggle: true,//卡片视图
                toolbar: '#toolbar',//工具栏

                //【样式设置】
                height: 384,
                //按需求设置不同的样式：5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
                rowStyle: function (row, index) {
                    var style = "";
                    if (row.username == "千锋教育") {
                        style = 'success';
                    }
                    if (row.username == "传智播客") {
                        style = 'info';
                    }
                    if (row.username == "lucifer") {
                        style = 'danger';
                    }
                    if (row.username == "一加") {
                        style = "active";
                    }
                    if (row.username == "联想") {
                        style = "warning";
                    }
                    return {classes: style}
                },

                //【设置列】
                columns: [
                    {field: 'loginname', title: '登录名'},
                    {field: 'password', title: '密码'},
                    {field: 'username', title: '用户名'},
                   
                    {
                        field: 'createdate', title: '创建时间',
                        formatter: function (value) {
                            return value.substr(0, 10);
                        }
                    },
                ]
            })
        }
    }

    var bstpTable = new BstpTable($("table"));
    bstpTable.inint({})

    //查询按钮的逻辑
    $("#search_btn").click(function () {
        $("#table").bootstrapTable("refresh", {url: '${ctx}/userJson'})
    })

</script>
</body>
</html>