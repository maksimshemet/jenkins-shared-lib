import com.org.common.Utils

def call(Map config = [:]){

    def utils = new Utils()
    String registrySecretId = "registry-secret"

    node {
        checkout scm

        stage('Code Scan'){
            sh "echo 'placeholder for code scan pipeline'"
        }
        stage('Unit testing'){
            def testImage = docker.build("test-image", "./dockerfiles/test_env") 

            testImage.inside {
                sh 'cd app && python test.py'
            }
        }
        stage('Build artifact'){
            

            utils.writeDockerConfig(this, registrySecretId)
        }
    }
    
}