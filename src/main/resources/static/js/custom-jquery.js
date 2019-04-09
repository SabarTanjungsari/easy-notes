
$(document).ready(function () {
    var table = $('#noteTable').DataTable({
        "sAjaxSource": "/api/note",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "id"},
            {"mData": "title"}
        ]
    })
});