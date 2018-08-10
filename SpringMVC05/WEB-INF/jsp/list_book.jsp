<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>show books</title>
    </head>
    <body>
        <div id="global">
            <h1>These are all of the books.</h1><br/>
            <a href="/SpringMVC05/input_insert_book"/>Add Book</a>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Category</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.category.name}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td><a href="/SpringMVC05/input_update_book/${book.id}">edit</a></td>
                    <td><a href="/SpringMVC05/delete_book/${book.id}">delete</a></td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <br/>
        <h2>${message}</h2>
    </body>
</html>