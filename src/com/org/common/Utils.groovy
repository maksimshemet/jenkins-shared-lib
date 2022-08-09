package com.org.common

def writeDockerConfig(def steps, String secret){
    
    def configJson = libraryResource 'docker/config.json'

    steps.sh """
    [ -d $HOME/.docker ] || mkdir .docker
    """
    steps.writeFile file: "$HOME/.docker/config.json" text: configJson
}