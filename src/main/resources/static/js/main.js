$(document).ready(function () {

    $("#postLongUrl").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        long_url_submit();

    });

});

function long_url_submit() {

    var longUrl = {
        longUrl: $("#longUrl").val()
    }

    $("#btn-submit").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "create-short",
        data: JSON.stringify(longUrl),
        dataType: 'text',
        cache: false,
        timeout: 600000,
        success: function (data) {
            document.getElementById("shortedUrl").style.display = 'inline';
            var short = $('#shortUrl');
            short.val(window.location + data);
            document.getElementById("shortUrl").onclick = function() {
                this.select();
                document.execCommand('copy');
            }

            console.log("SUCCESS : ", data);
            $("#btn-submit").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-submit").prop("disabled", false);

        }
    });

}