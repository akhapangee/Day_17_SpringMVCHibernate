<%@include file="../shared/header.jsp" %>
<div class="page-header">
    <h1><a href="${SITE_URL}/">HOME</a></h1>
    <h1><a href="${SITE_URL}/customer/json">GET JSON Customer Data</a></h1>
    <h1>Customers</h1>
</div>
<div class="pull-right">
    <a href="${SITE_URL}/customer/add" class="btn btn-primary btn_xs">
        <span class="glyphicon glyphicon-plus"/>
    </a>
</div>
<table class="table" id="customer-table">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Contact</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

</table>
<script>
    $(document).ready(function () {
        var $table = $("#customer-table");
        var $header = $table.find("tr").first();
        $table.find("tr").remove();
        $table.append($header);
        $.getJSON("${SITE_URL}/api/customer", function (res) {
            $.each(res, function (i, row) {
                var $tr = $("<tr/>");
                $("<td>").html(row.id).appendTo($tr);
                $("<td>").html(row.firstName + " " + row.lastName).appendTo($tr);
                $("<td>").html(row.email).appendTo($tr);
                $("<td>").html(row.contactNo).appendTo($tr);
                if (row.status) {
                    $("<td>").html('<label class="label label-success">Active</label>').appendTo($tr);
                } else {
                    $("<td>").html('<label class="label label-danger">Inactive</label>').appendTo($tr);
                }
                
                $("<td/>").html("Edit Delete").appendTo($tr);
                $tr.appendTo($table);
            });
        });
    });
</script>

<%@include file="../shared/footer.jsp" %>