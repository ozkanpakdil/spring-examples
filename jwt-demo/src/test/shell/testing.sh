#!/bin/bash
set -xe

PORT=8080

USER=$(tr -dc A-Za-z0-9 </dev/urandom | head -c 13; echo)

echo 'register user'
RESPONSE=$(curl -X 'POST' "http://localhost:$PORT/register" \
-H 'accept: */*' \
-H 'Content-Type: application/json;charset=utf-8' \
-d @- << EOF
{
"login": "$USER",
"password": "password",
"name": "string",
"surname": "string",
"birthDate": "2025-03-31",
"sex": "MALE"
}
EOF
)
echo "save the token"
USER_TOKEN=$(echo "$RESPONSE" | jq -r '.token')

echo 'admin login'
RESPONSE=$(curl --location "http://localhost:${PORT}/login" \
--header 'Content-Type: application/json' \
--data '{
"login": "admin",
"password": "admin"
}')
echo 'Save admin login'
ADMIN_TOKEN=$(echo "$RESPONSE" | jq -r '.token')

echo '-----------NEWS TESTING-----------'

echo 'Create news'
curl --location "http://localhost:${PORT}/news" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer $USER_TOKEN" \
--data '{
"text": "My first news",
"photoPaths": []
}' | jq .
echo ''

echo 'Lets view the news'
curl --location "http://localhost:${PORT}/news" | jq .
echo ''

echo 'Lets edit the news'
curl --location --request PUT "http://localhost:${PORT}/news/2" \
--header "Content-Type: application/json" \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"text": "My edited first news",
"photoPaths": []
}' | jq .

echo 'Lets view the news'
curl --location "http://localhost:${PORT}/news/2" | jq .
echo ''

echo 'Add a picture to the news'

IMAGE_PATH_RESPONSE=$(curl --location "http://localhost:${PORT}/photo" \
--header "Authorization: Bearer ${USER_TOKEN}" \
--form "file=@testImage.jpg")

IMAGE_PATH=$(echo "$IMAGE_PATH_RESPONSE" | jq -r '.path')

curl --location --request PUT "http://localhost:${PORT}/news/2" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data "{
\"text\": \"My edited first news\",
\"photoPaths\": [\"${IMAGE_PATH}\"]
}" | jq .

echo 'View the news'
curl --location "http://localhost:${PORT}/news/2" | jq .
echo ''

echo 'Delete the news'
curl --location --request DELETE "http://localhost:${PORT}/news/2" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq .

echo 'View all news'
curl --location "http://localhost:${PORT}/news" | jq .
echo ''

echo '-----------TESTING SUBSCRIPTIONS----------'
echo 'Lets see the list of available subscriptions'
curl --location "http://localhost:${PORT}/subscriptions/available" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq .
echo ''

echo ' get a free subscription'
curl --location "http://localhost:${PORT}/subscriptions/subscribe" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"subscriptionName": "Dating",
"monthCount": 12,
"bankDetails": {}
}' | jq .
echo ''

echo ' sign up for a paid subscription'
curl --location "http://localhost:${PORT}/subscriptions/subscribe" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"subscriptionName": "Music",
"monthCount": 12,
"bankDetails": {
"bankAccountName": "MY NAME",
"bankAccountSurname": "MY SURNAME",
"cardNumber": "1234567812345678",
"validityPeriod": "04/32",
"cvv": "777"
}
}' | jq .
echo ''

echo 'View active subscriptions for the account'
curl --location "http://localhost:${PORT}/subscriptions" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq .
echo ''

echo 'Cancel a paid subscription'
curl --location --request DELETE "http://localhost:${PORT}/subscriptions/unsubscribe" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"subscriptionName": "Music"
}' | jq .

echo 'View active subscriptions of the account'
curl --location "http://localhost:${PORT}/subscriptions" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq .
echo ''

echo '----------PROFILE MANAGEMENT TESTING-----------'

echo 'View all profiles'
curl --location "http://localhost:${PORT}/profiles" | jq .
echo ''

echo 'View user profile'
curl --location "http://localhost:${PORT}/profiles/user" | jq .
echo ''

echo 'Get a list of languages'
curl --location "http://localhost:${PORT}/languages" | jq .
echo ''

echo 'Set up profile information and add user admin to the family'
curl --location --request PUT "http://localhost:${PORT}/profiles/3" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"login": "user",
"shortDescription": "updated",
"homeTown": null,
"knownLanguages": ["Русский"],
"maritalStatus": null,
"familyLogins": ["admin"]
}' | jq .
echo ''

echo 'View all profiles'
curl --location "http://localhost:${PORT}/profiles" | jq .
echo ''

echo 'View user personal data'
curl --location "http://localhost:${PORT}/accounts" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq .
echo ''

echo 'Send a request to change the name'
curl --location --request PUT "http://localhost:${PORT}/accounts" \
--header 'Content-Type: application/json' \
--header "Authorization: Bearer ${USER_TOKEN}" \
--data '{
"name": "newName",
"surname": "surname"
}'
echo ''

echo 'The admin will receive all requests to change personal data'
curl --location "http://localhost:${PORT}/admin/requests" \
--header "Authorization: Bearer ${ADMIN_TOKEN}" | jq .
echo ''

echo 'Approve the change'
curl --location --request PUT "http://localhost:${PORT}/admin/requests/1/accept" \
--header "Authorization: Bearer ${ADMIN_TOKEN}" | jq .
echo ''

echo 'View the user profile'
curl --location "http://localhost:${PORT}/profiles/user" | jq .
echo ''

echo 'View the user`s personal data'
curl --location "http://localhost:${PORT}/accounts" \
--header "Authorization: Bearer ${USER_TOKEN}" | jq