def call(Map config = [:]){

    node {
        stage('Test'){
            sh "echo 'Hello world!!'"
        }
    }
    
}