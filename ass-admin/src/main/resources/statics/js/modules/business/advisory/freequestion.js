$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'advisory/freequestion/list',
        datatype: "json",
        colModel: [			
			{ label: '问答ID', name: 'id', index: "id", width: 45, key: true },
			{ label: '付费咨询师ID', name: 'consultantId', width: 75 },
			{ label: '咨询者ID', name: 'userpId', width: 75 },
			{ label: '付费咨询师值班日', name: 'dutyDay', width: 75 },
			{ label: '提问', name: 'question', width: 75 },
			{ label: '提问类型', name: 'questionMediaType', width: 90 },
			{ label: '提问时间', name: 'questionTime', width: 100 },
            { label: '回答', name: 'answer', width: 100 },
            { label: '回答类型', name: 'answerMediaType', width: 100 },
            { label: '回答时间', name: 'answerTime', width: 100 },
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
        	question: null
        },
        showList: true,
        title:null,
        freequestion:{
            id:null,
            consultantId:null,
            userpId:null,
            dutyDay:null,
            question:null,
            questionMediaType:null,
            questionTime:null,
            answer:null,
            answerMediaType:null,
            answerTime:null,
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
            vm.freequestion = {topFlag:"0", onlineFlag:"1", delFlag:"0"};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.getFreequestion(id);
        },
        del: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseBusinessURL + "advisory/freequestion/del",
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
            var url = vm.freequestion.id == null ? "advisory/freequestion/save" : "advisory/freequestion/update";
            $.ajax({
                type: "POST",
                url: baseBusinessURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.freequestion),
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
        getFreequestion: function(id){
            $.get(baseBusinessURL + "advisory/freequestion/info/" + id, function(r){
                vm.freequestion = r.freequestion;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'question': vm.q.question},
                page:page
            }).trigger("reloadGrid");
        }
    }
});