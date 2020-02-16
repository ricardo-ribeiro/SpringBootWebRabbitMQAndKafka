#!/bin/bash


function uiCreate(){
    npx create-react-app
}

# $1 - Name
function generateKeyPair(){

    ssh-keygen -t rsa -N '' -b 4096 -m PEM -f $1.key
    # Don't add passphrase
    openssl rsa -in $1.key -pubout -outform PEM -out $1.key.pub

    echo "Generated Key Pair"

    cat $1.key
    cat $1.key.pub

}

function generateJWTKeyPair() {
    rm src/main/resources/private/jwt_authentication_RS256.key
    rm src/main/resources/private/jwt_authentication_RS256.key.pub
    generateKeyPair src/main/resources/private/jwt_authentication_RS256
}