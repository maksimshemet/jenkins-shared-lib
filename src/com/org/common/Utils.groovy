package com.org.common

def writeDockerConfig(def steps, String secret){
    
    String configJson = libraryResource 'docker/config.json'

    steps.sh """
    [ -d $HOME/.docker ] || mkdir $HOME/.docker
    """

    steps.echo configJson
    steps.writeFile(file: "$HOME/.docker/config.json", text: configJson)

    steps.sh """
    sed -i 's/secret-token/${secret}/g' $HOME/.docker/config.json
    """
}