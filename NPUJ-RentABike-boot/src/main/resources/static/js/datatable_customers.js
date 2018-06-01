$(document).ready( function () {
	 var table = $('#customerTable').DataTable({
			"sAjaxSource": "/api/customers",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "name", 
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='http://localhost:8080/customers/details/"+oData.id+"'>"+oData.name+" "+oData.surname+"</a>");
			    	}
		        },
		        { "mData": "membershipType.name" },
				{ "mData": "membershipType.discountRate" },
				{ "mData": "id", 
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='http://localhost:8080/customers/edit/"+oData.id+"'>Edit</a>");
			    	}
		        },
				{ "mData": "id", 
			    	fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
			    		$(nTd).html("<a href='http://localhost:8080/customers/delete/"+oData.id+"'>Delete</a>");
			    	}
		        }
			]
	 })
});