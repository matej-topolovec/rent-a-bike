$(document).ready( function () {
	 var table = $('#reservationTable').DataTable({
			"sAjaxSource": "/api/reservations",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "startTime"},
		        { "mData": "endTime"},
		        { "mData": "customer.name" }
				
			]
	 })
});