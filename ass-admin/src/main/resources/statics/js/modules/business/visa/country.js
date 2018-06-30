$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'visa/country/list',
        datatype: "json",
        colModel: [			
			{ label: '国家ID', name: 'id', index: "id", width: 45, key: true },
			{ label: '国家名称', name: 'nationalName', width: 75 },
			{ label: '国家英文名称', name: 'nationalEnglishName', width: 90 },
			{ label: '国家二字码', name: 'nationalCode', width: 100 },
            { label: '国家图片', name: 'nationalFlag', width: 100 },
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
            nationalName: null
        },
        showList: true,
        title:null,
        country:{
            id:null,
            nationalName:null,
            nationalEnglishName:null,
            nationalCode:null,
            nationalFlag:null,
            area:null,
            hotVisa:null,
            topDescription:null,
            topFlag:"0",
            orderFlag:null,
            onlineFlag:"1",
            minPrice:null,
            maxPrice:null,
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
            vm.country = {topFlag:"0", onlineFlag:"1", delFlag:"0"};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getCountry(id);
        },
        del: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseBusinessURL + "visa/country/del",
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
            var url = vm.country.id == null ? "visa/country/save" : "visa/country/update";
            $.ajax({
                type: "POST",
                url: baseBusinessURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.country),
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
        getCountry: function(id){
            $.get(baseBusinessURL + "visa/country/info/" + id, function(r){
                vm.country = r.country;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'nationalName': vm.q.nationalName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});