# Example Spring Boot Application

This is an example Spring Boot application that demonstrates the use of JSON Web Tokens (JWT) for authentication and authorization.

## Features

- Spring Boot framework
- JWT-based authentication and authorization
- Example endpoints for testing

## Testing

For further testing, you can use the `testing.sh` script, which contains example `curl` calls to interact with the application. Make sure the application is running before executing the script.

## Running the Application

To run the application, use the following command:

```sh
./gradlew bootRun
```

## testing example output

```sh

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/jwt-demo/src/test/shell (master)
$ ./testing.sh 
+ PORT=8080
++ tr -dc A-Za-z0-9
++ head -c 13
++ echo
+ USER=kbyxRlpfK3MID
+ echo 'register user'
register user
++ curl -X POST http://localhost:8080/register -H 'accept: */*' -H 'Content-Type: application/json;charset=utf-8' -d @-
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   313    0   187  100   126   1869   1259 --:--:-- --:--:-- --:--:--  3161
+ RESPONSE='{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs"}'
+ echo 'save the token'
save the token
++ echo '{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs"}'
++ jq -r .token
+ USER_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs
+ echo 'admin login'
admin login
++ curl --location http://localhost:8080/login --header 'Content-Type: application/json' --data '{
"login": "admin",
"password": "admin"
}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   218    0   177  100    41   1896    439 --:--:-- --:--:-- --:--:--  2344
+ RESPONSE='{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkFETUlOIiwiaWF0IjoxNzQzODQzMDc1LCJleHAiOjE3NDM5ODcwNzV9.Yt4NninqnuhcopAnrA9MkvMoiv-1EOPD25X8lZvjjgc"}'
+ echo 'Save admin login'
Save admin login
++ echo '{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkFETUlOIiwiaWF0IjoxNzQzODQzMDc1LCJleHAiOjE3NDM5ODcwNzV9.Yt4NninqnuhcopAnrA9MkvMoiv-1EOPD25X8lZvjjgc"}'
++ jq -r .token
+ ADMIN_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkFETUlOIiwiaWF0IjoxNzQzODQzMDc1LCJleHAiOjE3NDM5ODcwNzV9.Yt4NninqnuhcopAnrA9MkvMoiv-1EOPD25X8lZvjjgc
+ echo '-----------NEWS TESTING-----------'
-----------NEWS TESTING-----------
+ echo 'Create news'
Create news
+ curl --location http://localhost:8080/news --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"text": "My first news",
"photoPaths": []
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    92    0    47  100    45   2305   2207 --:--:-- --:--:-- --:--:--  4600
{
  "id": 6,
  "text": "My first news",
  "photoPaths": []
}
+ echo ''

+ echo 'Lets view the news'
Lets view the news
+ curl --location http://localhost:8080/news
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   314    0   314    0     0  13983      0 --:--:-- --:--:-- --:--:-- 14272
[
  {
    "id": 1,
    "text": "Тестовая новость",
    "photoPaths": []
  },
  {
    "id": 2,
    "text": "My edited first news",
    "photoPaths": []
  },
  {
    "id": 3,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 4,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 5,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 6,
    "text": "My first news",
    "photoPaths": []
  }
]
+ echo ''

+ echo 'Lets edit the news'
Lets edit the news
+ curl --location --request PUT http://localhost:8080/news/2 --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"text": "My edited first news",
"photoPaths": []
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    52    0     0  100    52      0   2722 --:--:-- --:--:-- --:--:--  2736
+ echo 'Lets view the news'
Lets view the news
+ curl --location http://localhost:8080/news/2
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    54    0    54    0     0   3222      0 --:--:-- --:--:-- --:--:--  3375
{
  "id": 2,
  "text": "My edited first news",
  "photoPaths": []
}
+ echo ''

+ echo 'Add a picture to the news'
Add a picture to the news
++ curl --location http://localhost:8080/photo --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --form file=@testImage.jpg
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100 33496    0    21  100 33475    720  1121k --:--:-- --:--:-- --:--:-- 1127k
+ IMAGE_PATH_RESPONSE='{"path":"XWyEJm.jpg"}'
++ echo '{"path":"XWyEJm.jpg"}'
++ jq -r .path
+ IMAGE_PATH=XWyEJm.jpg
+ curl --location --request PUT http://localhost:8080/news/2 --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"text": "My edited first news",
"photoPaths": ["XWyEJm.jpg"]
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    64    0     0  100    64      0   3226 --:--:-- --:--:-- --:--:--  3368
+ echo 'View the news'
View the news
+ curl --location http://localhost:8080/news/2
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    54    0    54    0     0   3286      0 --:--:-- --:--:-- --:--:--  3375
{
  "id": 2,
  "text": "My edited first news",
  "photoPaths": []
}
+ echo ''

+ echo 'Delete the news'
Delete the news
+ curl --location --request DELETE http://localhost:8080/news/2 --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
+ echo 'View all news'
View all news
+ curl --location http://localhost:8080/news
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   314    0   314    0     0  16049      0 --:--:-- --:--:-- --:--:-- 16526
[
  {
    "id": 1,
    "text": "Тестовая новость",
    "photoPaths": []
  },
  {
    "id": 2,
    "text": "My edited first news",
    "photoPaths": []
  },
  {
    "id": 3,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 4,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 5,
    "text": "My first news",
    "photoPaths": []
  },
  {
    "id": 6,
    "text": "My first news",
    "photoPaths": []
  }
]
+ echo ''

+ echo '-----------TESTING SUBSCRIPTIONS----------'
-----------TESTING SUBSCRIPTIONS----------
+ echo 'Lets see the list of available subscriptions'
Lets see the list of available subscriptions
+ curl --location http://localhost:8080/subscriptions/available --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   168    0   168    0     0   9809      0 --:--:-- --:--:-- --:--:--  9882
[
  {
    "name": "Music",
    "description": "Подписка на музыку",
    "price": 250
  },
  {
    "name": "Dating",
    "description": "Подписка на знакомства",
    "price": 0
  }
]
+ echo ''

+ echo ' get a free subscription'
 get a free subscription
+ curl --location http://localhost:8080/subscriptions/subscribe --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"subscriptionName": "Dating",
"monthCount": 12,
"bankDetails": {}
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   278    0   209  100    69   9884   3263 --:--:-- --:--:-- --:--:-- 13238
{
  "id": 3,
  "login": "kbyxRlpfK3MID",
  "subscriptionType": {
    "name": "Dating",
    "description": "Подписка на знакомства",
    "price": 0
  },
  "startDate": "2025-04-05",
  "endDate": "2026-04-05",
  "nextPaymentDate": null
}
+ echo ''

+ echo ' sign up for a paid subscription'
 sign up for a paid subscription
+ curl --location http://localhost:8080/subscriptions/subscribe --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"subscriptionName": "Music",
"monthCount": 12,
"bankDetails": {
"bankAccountName": "MY NAME",
"bankAccountSurname": "MY SURNAME",
"cardNumber": "1234567812345678",
"validityPeriod": "04/32",
"cvv": "777"
}
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   419    0   210  100   209   8838   8796 --:--:-- --:--:-- --:--:-- 18217
{
  "id": 4,
  "login": "kbyxRlpfK3MID",
  "subscriptionType": {
    "name": "Music",
    "description": "Подписка на музыку",
    "price": 250
  },
  "startDate": "2025-04-05",
  "endDate": "2026-04-05",
  "nextPaymentDate": "2025-05-05"
}
+ echo ''

+ echo 'View active subscriptions for the account'
View active subscriptions for the account
+ curl --location http://localhost:8080/subscriptions --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   422    0   422    0     0  19799      0 --:--:-- --:--:-- --:--:-- 20095
[
  {
    "id": 3,
    "login": "kbyxRlpfK3MID",
    "subscriptionType": {
      "name": "Dating",
      "description": "Подписка на знакомства",
      "price": 0
    },
    "startDate": "2025-04-05",
    "endDate": "2026-04-05",
    "nextPaymentDate": null
  },
  {
    "id": 4,
    "login": "kbyxRlpfK3MID",
    "subscriptionType": {
      "name": "Music",
      "description": "Подписка на музыку",
      "price": 250
    },
    "startDate": "2025-04-05",
    "endDate": "2026-04-05",
    "nextPaymentDate": "2025-05-05"
  }
]
+ echo ''

+ echo 'Cancel a paid subscription'
Cancel a paid subscription
+ curl --location --request DELETE http://localhost:8080/subscriptions/unsubscribe --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"subscriptionName": "Music"
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    31    0     0  100    31      0   1211 --:--:-- --:--:-- --:--:--  1240
+ echo 'View active subscriptions of the account'
View active subscriptions of the account
+ curl --location http://localhost:8080/subscriptions --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   414    0   414    0     0  16019      0 --:--:-- --:--:-- --:--:-- 16560
[
  {
    "id": 3,
    "login": "kbyxRlpfK3MID",
    "subscriptionType": {
      "name": "Dating",
      "description": "Подписка на знакомства",
      "price": 0
    },
    "startDate": "2025-04-05",
    "endDate": "2026-04-05",
    "nextPaymentDate": null
  },
  {
    "id": 4,
    "login": "kbyxRlpfK3MID",
    "subscriptionType": {
      "name": "Music",
      "description": "Подписка на музыку",
      "price": 250
    },
    "startDate": "2025-04-05",
    "endDate": "2025-05-05",
    "nextPaymentDate": null
  }
]
+ echo ''

+ echo '----------PROFILE MANAGEMENT TESTING-----------'
----------PROFILE MANAGEMENT TESTING-----------
+ echo 'View all profiles'
View all profiles
+ curl --location http://localhost:8080/profiles
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   979    0   979    0     0  28531      0 --:--:-- --:--:-- --:--:-- 28794
[
  {
    "id": 1,
    "login": "admin",
    "shortDescription": "my status",
    "homeTown": "Саратов",
    "knownLanguages": [
      "Русский"
    ],
    "maritalStatus": "DATING",
    "familyLogins": [
      "test"
    ]
  },
  {
    "id": 2,
    "login": "test",
    "shortDescription": "test",
    "homeTown": "Саратов",
    "knownLanguages": [],
    "maritalStatus": "MARRIED",
    "familyLogins": [
      "admin"
    ]
  },
  {
    "id": 3,
    "login": "OCkJMFsoQA2Lr",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 4,
    "login": "x9VGSleMakrnp",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 5,
    "login": "q7vFyZBO2ZiT0",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 6,
    "login": "I5Q5APtHnS1r5",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 7,
    "login": "kbyxRlpfK3MID",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  }
]
+ echo ''

+ echo 'View user profile'
View user profile
+ curl --location http://localhost:8080/profiles/user
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
+ echo ''

+ echo 'Get a list of languages'
Get a list of languages
+ curl --location http://localhost:8080/languages
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    59    0    59    0     0   1520      0 --:--:-- --:--:-- --:--:--  1552
[
  {
    "name": "Английский"
  },
  {
    "name": "Русский"
  }
]
+ echo ''

+ echo 'Set up profile information and add user admin to the family'
Set up profile information and add user admin to the family
+ curl --location --request PUT http://localhost:8080/profiles/3 --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"login": "user",
"shortDescription": "updated",
"homeTown": null,
"knownLanguages": ["Русский"],
"maritalStatus": null,
"familyLogins": ["admin"]
}'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   149    0     0  100   149      0   3307 --:--:-- --:--:-- --:--:--  3386
+ echo ''

+ echo 'View all profiles'
View all profiles
+ curl --location http://localhost:8080/profiles
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   979    0   979    0     0  20564      0 --:--:-- --:--:-- --:--:-- 20829
[
  {
    "id": 1,
    "login": "admin",
    "shortDescription": "my status",
    "homeTown": "Саратов",
    "knownLanguages": [
      "Русский"
    ],
    "maritalStatus": "DATING",
    "familyLogins": [
      "test"
    ]
  },
  {
    "id": 2,
    "login": "test",
    "shortDescription": "test",
    "homeTown": "Саратов",
    "knownLanguages": [],
    "maritalStatus": "MARRIED",
    "familyLogins": [
      "admin"
    ]
  },
  {
    "id": 3,
    "login": "OCkJMFsoQA2Lr",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 4,
    "login": "x9VGSleMakrnp",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 5,
    "login": "q7vFyZBO2ZiT0",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 6,
    "login": "I5Q5APtHnS1r5",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  },
  {
    "id": 7,
    "login": "kbyxRlpfK3MID",
    "shortDescription": null,
    "homeTown": null,
    "knownLanguages": [],
    "maritalStatus": null,
    "familyLogins": []
  }
]
+ echo ''

+ echo 'View user personal data'
View user personal data
+ curl --location http://localhost:8080/accounts --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    98    0    98    0     0   1815      0 --:--:-- --:--:-- --:--:--  1849
{
  "login": "kbyxRlpfK3MID",
  "name": "string",
  "surname": "string",
  "birthDate": "2025-03-31",
  "sex": "MALE"
}
+ echo ''

+ echo 'Send a request to change the name'
Send a request to change the name
+ curl --location --request PUT http://localhost:8080/accounts --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs' --data '{
"name": "newName",
"surname": "surname"
}'
Запрос на изменение данных аккаунта отправлен+ echo ''

+ echo 'The admin will receive all requests to change personal data'
The admin will receive all requests to change personal data
+ curl --location http://localhost:8080/admin/requests --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkFETUlOIiwiaWF0IjoxNzQzODQzMDc1LCJleHAiOjE3NDM5ODcwNzV9.Yt4NninqnuhcopAnrA9MkvMoiv-1EOPD25X8lZvjjgc'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   179    0   179    0     0   2934      0 --:--:-- --:--:-- --:--:--  2934
[
  {
    "id": 2,
    "accountId": {
      "login": "kbyxRlpfK3MID",
      "name": "string",
      "surname": "string",
      "birthDate": "2025-03-31",
      "sex": "MALE"
    },
    "requestedChanges": {
      "name": "newName",
      "surname": "surname"
    }
  }
]
+ echo ''

+ echo 'Approve the change'
Approve the change
+ curl --location --request PUT http://localhost:8080/admin/requests/1/accept --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkFETUlOIiwiaWF0IjoxNzQzODQzMDc1LCJleHAiOjE3NDM5ODcwNzV9.Yt4NninqnuhcopAnrA9MkvMoiv-1EOPD25X8lZvjjgc'
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
+ echo ''

+ echo 'View the user profile'
View the user profile
+ curl --location http://localhost:8080/profiles/user
+ jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
+ echo ''

+ echo 'View the user`s personal data'
View the user`s personal data
+ curl --location http://localhost:8080/accounts --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrYnl4UmxwZkszTUlEIiwic3ViIjoiVVNFUiIsImlhdCI6MTc0Mzg0MzA3NSwiZXhwIjoxNzQzOTg3MDc1fQ.eGgX7VkIQx_tcQ7VIRbh5Tm0hIOFVBVwm5pBZjJZyGs'
+ jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    98    0    98    0     0   1838      0 --:--:-- --:--:-- --:--:--  1849
{
  "login": "kbyxRlpfK3MID",
  "name": "string",
  "surname": "string",
  "birthDate": "2025-03-31",
  "sex": "MALE"
}

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/jwt-demo/src/test/shell (master)
$

```