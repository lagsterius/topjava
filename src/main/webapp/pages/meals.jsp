<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
    <head>
        <title>Meals list</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">

    </head>

    <body>
        <h3><a href="/topjava/index.html">Home</a></h3>
        <h2>Meals list</h2>

        <table>
            <tr>
                <th>ID</th>
                <th>Date/Time</th>
                <th>Description</th>
                <th>Calories</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            <c:forEach items="${mealList}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                <tr class="${meal.exceed ? 'withExceed' : 'withoutExceed'}">
                    <form method="post" action="meals">
                        <td><input type="number" name="id" value="${meal.id}" readonly></td>
                        <td><input type="datetime-local" name="dateTime" value="${meal.dateTime}" ${editedId!=meal.id ? 'readonly' : ''}/></td>
                        <td><input type="text" name="description" value="${meal.description}" ${editedId!=meal.id ? 'readonly' : ''}/></td>
                        <td><input type="number" name="calories" value="${meal.calories}" ${editedId!=meal.id ? 'readonly' : ''}/></td>
                        <td>
                            ${editedId==meal.id ?
                                '<input type="submit" value="save"/>' :
                                '<a class="'.concat(meal.exceed ? 'withExceed' : 'withoutExceed').concat('" href="meals?action=edit&id=').concat(meal.id).concat('">Edit</a>')}
                        </td>
                        <td><a class="${meal.exceed ? 'withExceed' : 'withoutExceed'}" href="meals?action=remove&id=${meal.id}">Remove</a></td>
                    </form>
                </tr>
            </c:forEach>

            <tr>
                <form method="post" action="meals">
                    <td><input type="number" readonly></td>
                    <td><input type="datetime-local" name="dateTime"/></td>
                    <td><input type="text" name="description"/></td>
                    <td><input type="number" name="calories"/></td>
                    <td><input type="submit" value="Add"/></td>
                    <td><a href="./meals">Clear</a></td>
                </form>
            </tr>
        </table>
    </body>
</html>