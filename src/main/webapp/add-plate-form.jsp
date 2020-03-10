<!DOCTYPE html>
<html>
<head>
    <title>Add Plate</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-plate-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Plates</h2>
    </div>
</div>

<div id="container">
    <h3>Add Plate</h3>

    <form action="PlateControllerServlet" method="GET">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Plate type:</label></td>
                <td><input type="text" name="plateType" /></td>
            </tr>

            <tr>
                <td><label>Plate manufacturer:</label></td>
                <td><input type="text" name="plateManufacturer" /></td>
            </tr>

            <tr>
                <td><label>Plate Thickness</label></td>
                <td><input type="text" name="plateThickness" /></td>
            </tr>

            <tr>
                <td><label>Plate vendor code</label></td>
                <td><input type="text" name="plateVCode" /></td>
            </tr>

            <tr>
                <td><label>Plate size</label></td>
                <td><input type="text" name="plateSizes" /></td>
            </tr>

            <tr>
                <td><label>Plate description</label></td>
                <td><input type="text" name="plateDescription" /></td>
            </tr>

            <tr>
                <td><label>Plate price</label></td>
                <td><input type="text" name="platePrice" /></td>
            </tr>

            <tr>
                <td><label>Plate coefficient</label></td>
                <td><input type="text" name="plateCoefficient" /></td>
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
