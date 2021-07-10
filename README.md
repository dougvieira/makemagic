# Faça a magia - Desafio de backend



### 1 - Criação de Personagem:

```
Método: POST
Host: http://localhost:8080/api/characters
Body: {
    "name": "Harry Potter",
    "role": "student",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "houseId": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
    "patronus": "stag"
}
```


### 2 - Atualizando Personagem:

```
Método: PUT
Host: http://localhost:8080/api/characters
Body: {
    "id": 1,
    "name": "Harry Potter",
    "role": "studente",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "houseId": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
    "patronus": "stag"
}
```


### 3 - Deletando Personagem:

```
Método: DELETE
Host: http://localhost:8080/api/characters
Body: {
    "id": 1,
    "name": "Harry Potter",
    "role": "studente",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "houseId": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
    "patronus": "stag"
}
```


### 4 - Consultando todos Personagens:

```
Método: GET
Host: http://localhost:8080/api/characters
```


### 5 - Consultando todos Personagens:

```
Método: GET
Host: http://localhost:8080/api/characters
```

### 6 - Consultando Personagens de uma casa:

```
Método: GET
Host: http://localhost:8080/api/characters?house=1760529f-6d51-4cb1-bcb1-25087fce5bde
```

