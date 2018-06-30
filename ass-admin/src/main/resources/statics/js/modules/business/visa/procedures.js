$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'visa/procedures/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 45, key: true },
            { label: '标题描述', name: 'title', width: 75 },
            { label: '内容', name: 'content', width: 90 },
            { label: '状态', name: 'delFlag', width: 60, formatter: function(value, options, row){
                return value === "0" ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            }},
            { label: '创建时间', name: 'createTime', index: "create_time", width: 85}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"pageNum",
            rows:"pageSize",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            title: null
        },
        showList: true,
        title:null,
        procedures:{
            id:null,
            title:null,
            content:null,
            delFlag:"0"
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.procedures = {delFlag:"0"};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getProcedures(id);
        },
        del: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseBusinessURL + "visa/procedures/del",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.procedures.id == null ? "visa/procedures/save" : "visa/procedures/update";
            $.ajax({
                type: "POST",
                url: baseBusinessURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.procedures),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getProcedures: function(id){
            $.get(baseBusinessURL + "visa/procedures/info/" + id, function(r){
                vm.procedures = r.procedures;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'title': vm.q.title},
                page:page
            }).trigger("reloadGrid");
        }
    }
});