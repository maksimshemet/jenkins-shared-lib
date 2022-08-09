package com.org.common

def writeDockerConfig(def steps, String secretId){
    
    String configJson = libraryResource 'docker/config.json'

    steps.sh """
    [ -d $HOME/.docker ] || mkdir $HOME/.docker
    """

    steps.echo configJson
    steps.writeFile(file: "$HOME/.docker/config.json", text: configJson)

    steps.withCredentials([string(credentialsId: secretId, variable: 'SECRET')]) {
        steps.sh 'sed -i "s/secret-token/$SECRET/g" $HOME/.docker/config.json'
    }
}