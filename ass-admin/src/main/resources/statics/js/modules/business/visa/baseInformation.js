$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'visa/baseInformation/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'baseInformationId', index: "baseInformationId", width: 45, key: true },
			{ label: '名称', name: 'name', width: 75 },
			{ label: '描述', name: 'description', width: 90 },
			{ label: '入境次数', name: 'enterCountryNum', width: 100 },
            { label: '停留天数', name: 'stayDay', width: 100 },
            { label: '有效期', name: 'validityTime', width: 100 },
            { label: '套餐内容', name: 'comboContent', width: 100 },
            { label: '办理时间', name: 'handleTime', width: 100 },
            { label: '是否需要面签', name: 'visaInterview', width: 60, formatter: function(value, options, row){
                return value === "0" ?
                    '<span class="label label-success">不需要</span>' :
                    '<span class="label label-danger">需要</span>';
            }},
            { label: '保证金', name: 'cashDeposit', width: 100 },
            { label: '处理范围', name: 'handleRange', width: 100 },
            { label: '套餐描述', name: 'comboDescription', width: 100 },
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
            name: null
        },
        showList: true,
        title:null,
        baseInformation:{
            baseInformationId:null,
            name:null,
            description:null,
            enterCountryNum:null,
            stayDay:null,
            validityTime:null,
            comboContent:null,
            handleTime:null,
            visaInterview:"0",
            cashDeposit:null,
            handleRange:null,
            comboDescription:null,
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
            vm.baseInformation = {visaInterview:"0", delFlag:"0"};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getBaseInformation(id);
        },
        del: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseBusinessURL + "visa/baseInformation/del",
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
            var url = vm.baseInformation.baseInformationId == null ? "visa/baseInformation/save" : "visa/baseInformation/update";
            $.ajax({
                type: "POST",
                url: baseBusinessURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.baseInformation),
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
        getBaseInformation: function(id){
            $.get(baseBusinessURL + "visa/baseInformation/info/" + id, function(r){
                vm.baseInformation = r.baseInformation;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});