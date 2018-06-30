$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'visa/visaCombo/list',
        datatype: "json",
        colModel: [
            { label: '套餐ID', name: 'comboId', index: "comboId", width: 30, key: true },
            { label: '国家ID', name: 'visaId', width: 30},
            { label: '国家名称', name: 'visaName', width: 45},
            { label: '套餐序号', name: 'comboNum', width: 30},
            { label: '套餐名字', name: 'name', width: 90},
            { label: '套餐描述', name: 'description', width: 120},
            { label: '行情价', name: 'marketPrice', width: 40},
            { label: '套餐价格', name: 'price', width: 40},
            { label: '套餐上线标识', name: 'onlineFlag', width: 30, formatter: function(value, options, row){
                return value === 1 ?
                    '<span class="label label-success">上线</span>' :
                    '<span class="label label-danger">下线</span>';
            }},
            { label: '状态', name: 'delFlag', width: 30, formatter: function(value, options, row){
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
            name: null,
            visaName:null
        },
        showList: true,
        title:null,
        countryList:[],
        baseInformationList:[],
        needKnowList:[],
        proceduresList:[],
        materialList:[],
        visaCombo:{
            comboId:null,
            visaId:null,
            comboNum:null,
            name:null,
            description:null,
            marketPrice:null,
            price:null,
            onlineFlag:null,
            delFlag:"0",

            baseInformationId:null,
            needKnowIds:[],
            proceduresIds:[],

            studentMaterialIds:[],
            studentMaterialDesc:null,
            officersMaterialIds:[],
            officersMaterialDesc:null,
            retireesMaterialIds:[],
            retireesMaterialDesc:null,
            freelancerMaterialIds:[],
            freelancerMaterialDesc:null,

            acceptEmail:null,
            acceptAddress:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.visaCombo = {onlineFlag:1, delFlag:"0", needKnowIds:[], proceduresIds:[],
                studentMaterialIds:[], officersMaterialIds:[], retireesMaterialIds:[], freelancerMaterialIds:[]};
            this.getCountryList();
            this.getBaseInformationList();
            this.getNeedKnowList();
            this.getProceduresList();
            this.getMaterialList();
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
                    url: baseBusinessURL + "visa/visaCombo/del",
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
            var url = vm.visaCombo.comboId == null ? "visa/visaCombo/save" : "visa/visaCombo/update";
            $.ajax({
                type: "POST",
                url: baseBusinessURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.visaCombo),
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
        getCountryList: function(){
            $.get(baseBusinessURL + "visa/country/list", function(r){
                vm.countryList = r.page.list;
            });
        },
        getBaseInformationList: function(){
            $.get(baseBusinessURL + "visa/baseInformation/list", function(r){
                vm.baseInformationList = r.page.list;
            });
        },
        getNeedKnowList: function(){
            $.get(baseBusinessURL + "visa/needKnow/list", function(r){
                vm.needKnowList = r.page.list;
            });
        },
        getProceduresList: function(){
            $.get(baseBusinessURL + "visa/procedures/list", function(r){
                vm.proceduresList = r.page.list;
            });
        },
        getMaterialList: function(){
            $.get(baseBusinessURL + "visa/material/list", function(r){
                vm.materialList = r.page.list;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name, 'visaName': vm.q.visaName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});