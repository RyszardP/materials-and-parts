<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of plates </title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Materials</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add  Plate -->

        <input type="button" value="Add Plate"
               onclick="window.location.href='add-plate-form.jsp'; return false;"
               class="add-plate-button"
        />

        <table>

            <tr>
                <th>Plate type</th>
                <th>Plate manufacturer</th>
                <th>Plate thickness</th>
                <th>Plate vendor code</th>
                <th>Plate size</th>
                <th>Plate description</th>
                <th>Plate price</th>
                <th>Plate coefficient</th>
            </tr>

            <c:forEach var="tempPlate" items="${PLATE_LIST}">

                <!-- set up a link for each plate -->
                <c:url var="tempLink" value="PlateControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="plateId" value="${tempPlate.plateId}"/>
                </c:url>

                <!-- set up a link to delete a plate -->
                <c:url var="deleteLink" value="PlateControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="plateId" value="${tempPlate.plateId}"/>
                </c:url>

                <tr>
                    <td> ${tempPlate.plateType} </td>
                    <td> ${tempPlate.plateManufacturer} </td>
                    <td> ${tempPlate.plateThickness} </td>
                    <td> ${tempPlate.plateVCode} </td>
                    <td> ${tempPlate.plateSizes} </td>
                    <td> ${tempPlate.plateDescription} </td>
                    <td> ${tempPlate.platePrice} </td>
                    <td> ${tempPlate.plateCoefficient} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this plate?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>

</body>
</html>
