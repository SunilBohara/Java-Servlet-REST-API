# Java-Servlet-REST-API
A straightforward Restful API built with Java Servlet

The routes for handling all CRUD operation looks like this <br>
api/users get all Users <br>
api/users/1234 get User with id = 1234 <br>
api/users add new User <br>
api/users/1234 update User with id = 1234 <br>
api/users/1234 remove User <br>

### GET
api/users || GET api/users/{passUserId}<br>
It returns a list of users in JSON format. You can also get a specific user by passing their id.
##
### POST
api/users <br>
You can send(POST) data using a JSON file. The key(parameters) for users are <br>
*id <br>
*name <br>
*email <br>
*gener <br>
*status <br>
E.g
```
{
    "id": 1234,
    "name": "Sunil Bohara",
    "email": "boharasunil4@gmail.com",
    "gender": "male",
    "status": "active"
}
```
##
### DELETE
api/users/{passUserId} <br>
Delete the user from the server (database) by entering the user ID into the endpoint. For example, using the endpoint like this: api/users/1234.
##
### PUT 
api/users/{passUserId} <br>
By passing the ID in the endpoint and sending the new data in JSON format, you can update the user. The keys (parameters) for users are <br>
*id <br>
*name <br>
*email <br>
*gener <br>
*status <br>
E.g
```
{
    "id": 1234,
    "name": "Sunil Bohara",
    "email": "boharasunil4@gmail.com",
    "gender": "male",
    "status": "active"
}
```
:) 
