Employee Management
--------------------

H2 In memory with Spring Security
---------------------------------
**Step 1**: Compile the project with jar :

    mvn clean install

**Step 2**: Run following command

	  mvn spring-boot:run

API Endpoint Details
---------------------

http://localhost:8080/api/
http://localhost:8080/api/login

user= admin

password= password


    http://localhost:8080/api/employees
{
"id": 1,
"firstName": "Parijatha",
"lastName": "Sudhaker",
"email": "pari@spr.com"
}

    http://localhost:8080/api/user
{"id":1,"userName":"parijatha","password":"sudhaker","active":"YES","roleId":2}

    http://localhost:8080/api/role
{"id":1,"roleId":"2","name":"USER"}

