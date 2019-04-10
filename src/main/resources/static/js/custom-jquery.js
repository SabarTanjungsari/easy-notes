$(document).ready(function () {

    // start note
    var table = $('#noteTable').DataTable({
        "sAjaxSource": "/api/note",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "id"},
            {"mData": "title"}
        ]
    })
    // end of note

    // start status
    var table = $('#statusTable').DataTable({
        "sAjaxSource": "/api/status",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "id"},
            {"mData": "name"},
            {"mData": "enabled"}
        ]
    })
    // end of the status
});