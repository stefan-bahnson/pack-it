#!/usr/bin/env bash
# POST users
curl -H "Content-type: application/json" -X POST -d '{
    "name":"Eckrin",
    "email":"email@mail.com",
    "password":"pw"}' http://localhost:8080/kanoting-web/users
curl -H "Content-type: application/json" -X POST -d '{
    "name":"Leka",
    "email":"email@mail.com",
    "password":"pw"}' http://localhost:8080/kanoting-web/users
curl -H "Content-type: application/json" -X POST -d '{
    "name":"Leppen",
    "email":"email@mail.com",
    "password":"pw"}' http://localhost:8080/kanoting-web/users
curl -H "Content-type: application/json" -X POST -d '{
    "name":"Laine",
    "email":"email@mail.com",
    "password":"pw"}' http://localhost:8080/kanoting-web/users
curl -H "Content-type: application/json" -X POST -d '{
    "name":"Eidel",
    "email":"email@mail.com",
    "password":"pw"}' http://localhost:8080/kanoting-web/users

# POST packlists
curl -H "Content-type: application/json" -X POST -d '{
  "name": "Eckrins packlist",
  "user": { "id": 1 }
}' http://localhost:8080/kanoting-web/packlists
curl -H "Content-type: application/json" -X POST -d '{
  "name": "Lekas packlist",
  "user": { "id": 2 }
}' http://localhost:8080/kanoting-web/packlists
curl -H "Content-type: application/json" -X POST -d '{
  "name": "Leppens packlist",
  "user": { "id": 3 }
}' http://localhost:8080/kanoting-web/packlists
curl -H "Content-type: application/json" -X POST -d '{
  "name": "Laines packlist",
  "user": { "id": 4 }
}' http://localhost:8080/kanoting-web/packlists
curl -H "Content-type: application/json" -X POST -d '{
  "name": "Eidels packlist",
  "user": { "id": 5 }
}' http://localhost:8080/kanoting-web/packlists

# PUT users to packlist
curl -H "Content-type: application/json" -X PUT -d '[
    { "id": 1},
    { "id": 2},
    { "id": 3}
]' http://localhost:8080/kanoting-web/packlists/1/users
curl -H "Content-type: application/json" -X PUT -d '[
    { "id": 2},
    { "id": 3},
    { "id": 4}
]' http://localhost:8080/kanoting-web/packlists/2/users
curl -H "Content-type: application/json" -X PUT -d '[
    { "id": 3},
    { "id": 4},
    { "id": 5}
]' http://localhost:8080/kanoting-web/packlists/3/users
curl -H "Content-type: application/json" -X PUT -d '[
    { "id": 4},
    { "id": 5},
    { "id": 1}
]' http://localhost:8080/kanoting-web/packlists/4/users
curl -H "Content-type: application/json" -X PUT -d '[
    { "id": 5},
    { "id": 1},
    { "id": 2}
]' http://localhost:8080/kanoting-web/packlists/5/users