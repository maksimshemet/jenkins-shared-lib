import com.org.common.Utils

def call(Map config = [:]){

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
            String secret = "secret"
            Utils.writeDockerConfig(self, secret)
        }
    }
    
}