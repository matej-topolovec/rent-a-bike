$(document).ready( function () {
	 var table = $('#bikeTable').DataTable({
			"sAjaxSource": "/api/bikes",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "name",  
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='http://localhost:8080/bike/details/" + oData.id +"'>" + oData.name + "</a>");
			    	}
		        },
		        { "mData": "dateAdded"},
		        { "mData": "quantity" },
				{ "mData": "available" },
				{ "mData": "biketype.name" },
				
				{ "mData": "id", 
					   fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
					    $(nTd).html("<a href='http://localhost:8080/bike/edit/" + oData.id + "'>Edit</a>");
					     }		
				
			    },
				
				{ "mData": "id", 
					   fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
					    $(nTd).html("<a href='http://localhost:8080/bike/delete/" + oData.id + "'>Delete</a>");
								}
				
				}
	 
				
			]
	 })
});