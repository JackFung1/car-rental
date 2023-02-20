# springboot-rest-h2-swagger
This is an example of Spring Boot + JPA + Rest + H2 + Swagger

This project is built as an example for any one to would like to have a base to start
1. Spring Boot
2. JPA
3. Rest Controller
4. H2
5. Swagger


# Test Cases

# Get Api example

Request:
http://localhost:8081/carRental/public/getFoods

Response:
[
{
"id": 1,
"name": "apple",
"calories": 100
},
{
"id": 2,
"name": "mango",
"calories": 200
},
{
"id": 3,
"name": "jack fruit",
"calories": 500
},
{
"id": 4,
"name": "orange",
"calories": 300
}
]

Request:
http://localhost:8081/carRental/public/getCarById/1

Response:
{
"id": 1,
"carModel": "Toyota Camey",
"carStock": 100
}

Request:
http://localhost:8081/carRental/public/getCars/1/2

Response:
[
{
"id": 1,
"carModel": "Toyota Camey",
"carStock": 100
},
{
"id": 2,
"carModel": "BMW 650",
"carStock": 200
}
]


# Add Car
Request: Post Method
http://localhost:8081/carRental/admin/addCar

Body:
{
"carModel": "test11",
"carStock": 100
}


# delete car
Request: Delete Method
http://localhost:8081/carRental/admin/removeCar/6

# Swagger UI URL
http://localhost:8081/carRental/swagger-ui.html

# Web Portal Client Demo
http://localhost:8081/carRental/index.html