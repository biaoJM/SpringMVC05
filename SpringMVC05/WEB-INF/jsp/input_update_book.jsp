<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>update</title>
    </head>
    <body>
        <h1>update</h1><br/>
        <h2>input new information of book ${id}</h2><br/>
        <form:form commandName="book" action="/SpringMVC05/update_book/${id}" method="POST">
            <fieldset>
            <table>
                <tr>
                    <td>ID:</td>
                    <td><form:input id="id" path="id" value="${oldBook.id}"/></td>
                </tr>
                <tr>
                    <td>NAME:</td>
                    <td><form:input id="name" path="name" value="${oldBook.name}"/></td>
                </tr>
                <tr>
                    <td>category id:</td>
                    <td><form:input id="categoryid" path="category.id" value="${oldBook.category.id}"/></td>
                </tr>
                <tr>
                    <td>category name:</td>
                    <td><form:input id="categoryname" path="category.name" value="${oldBook.category.name}"/></td>
                </tr>
                <tr>
                    <td>AUTHOR:</td>
                    <td><form:input id="author" path="author" value="${oldBook.author}"/></td>
                </tr>
                <tr>
                    <td>PRICE:</td>
                    <td><form:input id="price" path="price" value="${oldBook.price}"/></td>
                </tr>
            </table>
            <input type="submit"/>
            </fieldset>
        </form:form>
    </body>
</html>