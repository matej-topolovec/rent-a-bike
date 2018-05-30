$(document).ready( function () {
	 var table = $('#bikeTable').DataTable({
			"sAjaxSource": "/api/bikes",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "name",  
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='http://localhost:8080/BikeDetails/"+oData.id +"'>" + oData.name + "</a>");
			    	}
		        },
		        { "mData": "dateAdded"},
		        { "mData": "quantity" },
				{ "mData": "available" },
				{ "mData": "biketype.name" },
				
				{ 
					
					data: null,
	                className: "center",           
	                defaultContent: '<a href="http://localhost:8080/EditBike/"  + oData.id' + '"class="editor_edit">Edit</a> /  <a href="http://localhost:8080/DeleteBike/"  oData.id  class="editor_remove">Delete</a>'
	            //  defaultContent: '<a href="" class="editor_edit">Edit</a> / <a href="" class="editor_remove">Delete</a>'
				
				}
			]
	 })
});