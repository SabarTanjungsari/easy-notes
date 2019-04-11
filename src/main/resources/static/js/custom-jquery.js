$(function () {

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
    var save_method; // for save method string

    var table = $('#statusTable').DataTable({
        "sAjaxSource": "/api/status",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "id"},
            {"mData": "name"},
            {"mData": "enabled"}
        ]
    })

    // show modal status
    $('#btnAddStatus').click(function () {
        save_method = 'add';
        $("#formStatus")[0].reset(); // reset form on modal
        $("#statusModal").modal('show'); // show bootstrap modal
        $(".modal-header #statusModalLabel").text("Add New Contact");
    });

    var status = {};
    $('#btnSaveStatus').click(function () {
        status.name = $('#statusName').val();
        var statusJSON = JSON.stringify(status);
        $.ajax({
            url: '/admin/status/save',
            method: 'POST',
            data: statusJSON,
            contentType: "application/json; charset=utf-8",
            success: function () {
                alert('Saved successfully!');
                location.reload(true);
            },
            error: function (error) {
                alert(error);
            }
        })
    })

    // end of the status
});