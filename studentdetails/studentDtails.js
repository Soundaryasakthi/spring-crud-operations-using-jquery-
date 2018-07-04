function initial(){
	
	$("#Save").val('save');
	$('#loadEntry').hide();
	$('#dialog').hide();
	$("#studName").attr("disabled", true);
	$("#studAddress").attr("disabled", true);
	$("#studCreatedDate").attr("disabled", true);
	$("#studCreatedUser").attr("disabled", true);
	$("#edit").hide();
	$("#SaveUpdate").hide();
	$("#delete").hide();
	$("#Cancel").hide();
	$("#flag").val('save');
	$('#empId').val('');
}

function editing(id){

	$('#loadEntry').show();
	$('#dialog').show();
	$("#Save").prop('value', 'Update');
	tis_common_util.setHorizontalAlignmentCommon('#rightTopPane','#rightBottomPane');
	$("#studName").attr("disabled", true);
	$("#studAddress").attr("disabled", true);
	$("#studCreatedDate").attr("disabled", true);
	$("#studCreatedUser").attr("disabled", true);
	$("#edit").show();
	$("#delete").show();

	$.ajax({
		data:id,
		contentType : 'application/json',
		datatype : 'json',
		type : "POST",
		url:docurl+"/edit/",
		success:function(response){
			$("#flag").val('update');
//			alert(response[0])
			$('#studId').val(response[0][0]);
			$("#studName").val(response[0][1]);
			$("#studAddress").val(response[0][2]);
			$("#studCreatedDate").val(response[0][3]);
			$("#studCreatedUser").val(response[0][4]);
			response=null;
		},
		error:function(response){
			console.log(response);
		}
	
	});
	
}

var flag;

$(document).ready(function($) {
	initial();
	
	$('#studId').val('');
	$("#Save").prop('value', 'Save');
	var userRights = $('#userRights').val();
	var currentUser=$('#usrName').val();
	
	var flag = $('#flag').val();
	
	loadGrid();
	
	$("#addbtn").click(function(){
		$('#loadEntry').show();
		$('#studId').val('');
		$('#dialog').show();
		tis_common_util.setHorizontalAlignmentCommon('#rightTopPane','#rightBottomPane');
		$("#edit").hide();
		$("#delete").hide();
		$('#SaveUpdate').show();
		$("#Save").val('save');
		$("#Cancel").show();
		$("#studName").val('');
		$("#studAddress").val('');
		$("#estudCreatedDate").val('');
		$("#studCreatedUser").val('');
		$("#studName").prop("disabled", false);
		$("#studAddress").prop("disabled", false);
		$("#studCreatedDate").prop("disabled", false);
		$("#studCreatedUser").prop("disabled", false);
		$('#Save').attr('disabled', false);
		$("#flag").val('save');
		
	});
	
	
	$("#edit").click(function(){
		$("#studName").prop("disabled", false);
		$("#studAddress").prop("disabled", false);
		$("#studCreatedDate").prop("disabled", false);
		$("#studCreatedUser").prop("disabled", false);
		
		$("#Save").val('Update');
		$("#flag").val('update');
		
		$("#SaveUpdate").show();
		$('#Save').attr('disabled', false);
		$("#Cancel").show();
		
	});
	
	$("#close").click(function(){
		$("#studName").val('');
		$("#studAddress").val('');
		$("#studCreatedDate").val('');
		$("#studCreatedUser").val('');
		initial();
		$("#flag").val('save');
		document.getElementById('file').parentNode.innerHTML = document.getElementById('file').parentNode.innerHTML;
	});
	
	$("#Cancel").click(function(){
		$("#studName").val('');
		$("#studAddress").val('');
		$("#studCreatedDate").val('');
		$("#studCreatedUser").val('');
		$("#flag").val('save');
		initial();
	});
	
	$("#Save").click(function(){
		$('#Save').attr('disabled', 'disabled');
		
		saveUpdate();
		initial();
//		alert($("#studName").val()+" "+$("#studAddress").val()+" "+$("#studCreatedDate").val()+" "+$("#studCreatedUser").val());
	});
	
	$('#delete').click(function(){
		tis_common_util.custom_confirm_alert('deleteStudent', 'Are you sure you want to delete?');
		
	});
});

function loadGrid(){
	formData = [];
	var userRights = $('#userRights').val();
	$("#gridmain").jqGrid('GridUnload');
	$("#gridmain").jqGrid({
		
		url : docurl,
		mtype : 'GET',
		datatype : 'json',
		colNames : [ 'Id','Student Name',
				'Student Address',
				'createdDate',
				'createdUser' ],
		colModel : [
{
	name : 'id',
	index: "id", 
	
	hidden : true
},
			
			{
				name : 'name',
				index: "name",
				align : 'left',
				sortable : true,
				search : true,
				width : '70px',
				searchoptions: { searchhidden: true, sopt: ['cn','nc','eq', 'bw', 'ew']}
			},
			{
				name : 'address',
				index: "address",
				align : 'left',
				sortable : true,
				search : true,
				width : '70px',
				searchoptions: { searchhidden: true, sopt: ['cn','nc','eq', 'bw', 'ew']}
			},
			{
				name : 'createdDate',
				index: "createdDate",
				align : 'left',
				sortable : true,
				search : true,
				width : '70px',
				searchoptions: { searchhidden: true, sopt: ['cn','nc','eq', 'bw', 'ew']}
			},
			{
				name : 'createdUser',
				index: "createdUser",
				align : 'left',
				sortable : true,
				search : true,
				width : '70px',
				searchoptions: { searchhidden: true, sopt: ['cn','nc','eq', 'bw', 'ew']}
			}
			
		
		         ],
		            rowNum : 100,
					//rowTotal : 200,
					rowList: [100, 200, 300, 100000000],
					loadonce : true,
					rownumbers : true,
					height : '100%',
					width : '100%',
					cellEdit:true,
					gridview : true,
					multiselect : true,
					clearSearch : false,
					 autowidth : true,
						forceFit : false,
						shrinkToFit : false,
					pager : '#gridpage',
					viewrecords : true,
					viewsortcols : [true,'vertical',true],
					sortable : true,
					sortorder : "asc",
					
					
					ondblClickRow : function(rowId) {
						var k=$("#gridmain").getRowData(rowId);
						
						editing(k['id']);
					}
					
	});
}


function saveUpdate(){
//	alert($('#flag').val());
	var map={'id':$('#studId').val(),'name':$('#studName').val(), 'address':$('#studAddress').val(), 'createdDate':$('#studCreatedDate').val(), 'createdUser':$('#studCreatedUser').val()};
	$.ajax({
		data:JSON.stringify(map),
		contentType : 'application/json',
		type : "POST",
		url:docurl+"/"+$('#flag').val(),
		success:function(response){
			loadGrid();
			console.log(response);
			$("#studName").val('');
			$("#studAddress").val('');
			$("#studCreatedDate").val('');
			$("#studCreatedUser").val('');
			initial();
		},
		error:function(response){
			console.log(response);
		}
	
		
	});
	
	
}

function deleteStudent(){
	var id=$("#studId").val();
	$("#flag").val('delete');
	$.ajax({
		data:id,
		contentType : 'application/json',
		datatype : 'json',
		type : "POST",
		url:docurl+"/delete/",
		success:function(response){
			console.log(response);
			initial();
			loadGrid();
			 tis_common_util.custom_alert("S","Record Deleted Successfully")

		},
		error:function(response){
			console.log(response);
		}
		
	});
}

