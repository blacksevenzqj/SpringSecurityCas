$(function () {
    $("#jqGrid").jqGrid({
        url: baseBusinessURL + 'airTicket/airTicketOrder/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 45, key: true },
			{ label: '订单号', name: 'orderNum', width: 120 },
            { label: '订单往返', name: 'ticketOrderType', width: 40, formatter: function(value, options, row){
                return value === "1" ?
                    '<span class="label label-success">单程</span>' :
                    '<span class="label label-danger">往返</span>';
            }},
            { label: '订单类型', name: 'orderChangingType', width: 40, formatter: function(value, options, row){
                return value === "1" ?
                    '<span class="label label-success">默认新单</span>' :
                    '<span class="label label-danger">改签订单</span>';
            }},
            { label: '去程城市', name: 'goCityName', width: 50 },
            { label: '去程航班', name: 'goAirlineCompanyFlightNo', width: 100 },
			{ label: '下单人', name: 'userName', width: 40 },
            { label: '订单总人数', name: 'orderPeopleNum', width: 40 },
            { label: '订单支付价格（元）', name: 'orderAmount', width: 55 },
            { label: '订单状态', name: 'orderStatus', width: 60, formatter: function(value, options, row){
                if(value === '1'){
                    return '<span class="label label-success">未支付</span>';
                }else if(value === '2'){
                    return '<span class="label label-success">订单取消/成功</span>';
                }else if(value === '3'){
                    return '<span class="label label-danger">订单取消/失败</span>';
                }else if(value === '4'){
                    return '<span class="label label-success">支付成功</span>';
                }else if(value === '5'){
                    return '<span class="label label-danger">支付失败</span>';
                }else if(value === '6'){
                    return '<span class="label label-danger">过期未支付</span>';
                }else if(value === '7'){
                    return '<span class="label label-success">退款成功</span>';
                }else if(value === '8'){
                    return '<span class="label label-danger">退款失败</span>';
                }
            }},
            { label: '联系人', name: 'contactUserName', width: 40 },
            { label: '联系人电话', name: 'contactUserPhone', width: 60 },
            { label: '报销凭证', name: 'reimbursementVoucher', width: 30, formatter: function(value, options, row){
                return value === "0" ?
                    '<span class="label label-success">不需要</span>' :
                    '<span class="label label-danger">需要</span>';
            }},
			{ label: '状态', name: 'delFlag', width: 30, formatter: function(value, options, row){
				return value === "0" ?
					'<span class="label label-success">正常</span>' :
					'<span class="label label-danger">禁用</span>';
			}},
			{ label: '创建时间', name: 'createDate', index: "create_date", width: 70}
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
            orderNum: null,
            userpName: null,
            contactUserName: null,
            ticketOrderType: "1"
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
                postData:{'orderNum': vm.q.orderNum, 'userpName': vm.q.userpName, 'contactUserName': vm.q.contactUserName, 'ticketOrderType': vm.q.ticketOrderType},
                page:page
            }).trigger("reloadGrid");
        }
    }
});