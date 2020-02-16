#!/bin/bash

alias gca='./gradlew clean assemble'


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


function ChangeLog() {
     previous_tag=0
     for current_tag in $(git tag --sort=-creatordate)
     do
         if [ "$previous_tag" != 0 ];then
             tag_date=$(git log -1 --pretty=format:'%ad' --date=short ${previous_tag})
             printf "## ${previous_tag} (${tag_date})\n\n"
             git log ${current_tag}...${previous_tag} --pretty=format:'*  %s [View](https://bitbucket.org/projects/test/repos/my-project/commits/%H)' --reverse | grep -v Merge
             printf "\n\n"
         fi
     previous_tag=${current_tag}
     done
}