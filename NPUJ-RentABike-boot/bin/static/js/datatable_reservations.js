$(document).ready( function () {
	 var table = $('#reservationTable').DataTable({
			"sAjaxSource": "/api/reservations",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "startTime",
		        	"render": function (mData) {
		                var date = new Date(mData);
		                var month = date.getMonth() + 1;
		                return (month.length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
		            }},
		        { "mData": "endTime",
		        	"render": function (mData) {
		                var date = new Date(mData);
		                var month = date.getMonth() + 1;
		                return (month.length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
		            }},
		        { "mData": "customer.name" },
		        { "mData": "bike.name" }
				
			]
	 })
});