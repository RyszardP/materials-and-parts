<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of manufacturers </title>

    <link type="text/css" rel="stylesheet" href="/css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Manufacturers</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <input type="button" value="Add Manufacturer"
               onclick="window.location.href='add-manufacturer-form.jsp'; return false;"
               class="add-manufacturer-button"
        />

        <table>

            <tr>
                <th>Manufacturer name</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="tempManufacturer" items="${MANUFACTURER_LIST}">


                <c:url var="tempLink" value="/ManufacturerControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="manufacturerId" value="${tempManufacturer.manufacturerId}"/>
                </c:url>


                <c:url var="deleteLink" value="ManufacturerControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="manufacturerId" value="${tempManufacturer.manufacturerId}"/>
                </c:url>

                <tr>
                    <td> ${tempManufacturer.manufacturerTitle} </td>
                    <td>
                        <a href="${tempLink}">Update</a></td>
                    <td>
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this manufacturer?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>
</div>
</body>
</html>
