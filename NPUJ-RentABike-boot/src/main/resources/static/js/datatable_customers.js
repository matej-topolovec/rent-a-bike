$(document).ready( function () {
	 var table = $('#customerTable').DataTable({
			"sAjaxSource": "/api/customers",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "name", 
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='localhost:8080/customers/details/"+oData.id+"'>"+oData.name+"</a>");
			    	}
		        },
		        { "mData": "membershipType.name" },
				{ "mData": "membershipType.discountRate" },
				{
	                data: null,
	                className: "center",
	                defaultContent: '<a href="" class="editor_edit">Edit</a> / <a href="" class="editor_remove">Delete</a>'
	            }
			]
	 })
});