<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update plate</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-plate-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Materials</h2>
    </div>
</div>

<div id="container">
    <h3>Update plate</h3>

    <form action="PlateControllerServlet" method="GET">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="plateId" value="${THE_PLATE.plateId}" />

        <table>
            <tbody>
            <tr>
                <td><label>Plate type:</label></td>
                <td><input type="text" name="plateType"
                           value="${THE_PLATE.plateType}" /></td>
            </tr>

            <tr>
                <td><label>Plate manufacturer:</label></td>
                <td><input type="text" name="plateManufacturer"
                           value="${THE_PLATE.plateManufacturer}" /></td>
            </tr>

            <tr>
                <td><label>Plate Thickness</label></td>
                <td><input type="text" name="plateThickness"
                           value="${THE_PLATE.plateThickness}" /></td>
            </tr>

            <tr>
                <td><label>Plate Vendor Code</label></td>
                <td><input type="text" name="plateVCode"
                           value="${THE_PLATE.plateVCode}" /></td>
            </tr>

            <tr>
                <td><label>Plate Size</label></td>
                <td><input type="text" name="plateSizes"
                           value="${THE_PLATE.plateSizes}" /></td>
            </tr>

            <tr>
                <td><label>Plate Description</label></td>
                <td><input type="text" name="plateDescription"
                           value="${THE_PLATE.plateDescription}" /></td>
            </tr>

            <tr>
                <td><label>Plate Price</label></td>
                <td><input type="text" name="platePrice"
                           value="${THE_PLATE.platePrice}" /></td>
            </tr>

            <tr>
                <td><label>Plate Coefficient</label></td>
                <td><input type="text" name="plateCoefficient"
                           value="${THE_PLATE.plateCoefficient}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="PlateControllerServlet">Back to List</a>
    </p>
</div>

</body>
</html>
