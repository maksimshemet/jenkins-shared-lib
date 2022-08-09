import com.org.common.Utils

def call(Map config = [:]){

    def utils = new Utils()
    String registrySecretId = "registry-secret"
    String dockerRegistryUrl = "lab.registry.com"
    String registryRepo = "hwapi"
    String appName = "hello-api"

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
        stage('Build artifact and push to registry'){
            utils.writeDockerConfig(this, registrySecretId)

            withCheckout(scm) {
                String imageString = "${dockerRegistryUrl}/${registryRepo}/${appName}:${env.GIT_COMMIT}"
                sh """
                docker build -t ${imageString} .
                docker push ${imageString}
                """
            }
            
        }
    }
    
}