pipeline{
    agent {label 'build-vm'}
    stages{
        stage('gitclone'){
            steps{
                git branch: 'main', 
                url: 'https://github.com/zerotoprodevops/hello-war-04.git'
            }
        }
        stage('build'){
            steps{
                sh "docker build -t jyotimanab/helloapp:${env.BUILD_NUMBER} ."
            }
        }
        stage('test'){
            steps{
                sh "TMPDIR=/tempdir trivy image jyotimanab/helloapp:${env.BUILD_NUMBER}"
            }
        }
        stage('push to docker hub'){
            steps{
            withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
    sh '''
	echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin 
	docker push jyotimanab/helloapp:${BUILD_NUMBER}	
'''
            }
        }

        }
    }
    post {
        success{
            echo "Build ${env.BUILD_NUMBER} succeeded - image on docker hub"
            sh 'docker rmi jyotimanab/helloapp:${BUILD_NUMBER} || echo "Cleanup: image not found locally"'
        }
        failure{
            echo "Pipeline Failed - image kept locally for debugging"
        }
        
    }
}
