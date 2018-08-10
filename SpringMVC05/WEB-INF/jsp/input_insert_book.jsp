<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>insert</title>
    </head>
    <body>
        <h1>insert</h1><br/>
        <h2>input a new book's information</h2><br/>
        <form:form commandName="book" action="/SpringMVC05/insert_book" method="POST">
            <fieldset>
            <table>
                <tr>
                    <td>ID:</td>
                    <td><form:input id="id" path="id"/></td>
                </tr>
                <tr>
                    <td>NAME:</td>
                    <td><form:input id="name" path="name"/></td>
                </tr>
                <tr>
                    <td>category id:</td>
                    <td><form:input id="categoryid" path="category.id"/></td>
                </tr>
                <tr>
                    <td>category name:</td>
                    <td><form:input id="categoryname" path="category.name"/></td>
                </tr>
                <tr>
                    <td>AUTHOR:</td>
                    <td><form:input id="author" path="author"/></td>
                </tr>
                <tr>
                    <td>PRICE:</td>
                    <td><form:input id="Price" path="Price"/></td>
                </tr>
            </table>
            <input type="submit"/>
            </fieldset>
        </form:form>
    </body>
</html>