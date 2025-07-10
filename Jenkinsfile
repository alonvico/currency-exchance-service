pipeline {
    agent { label 'linux' }

    environment {
        DOCKER_IMAGE = 'alonvico/mmv3-currency-exchange-service:latest'
    }

    stages {

        stage ('Compile') {
            steps {
                echo 'Compiling...'
                sh 'mvn clean compile'
            }
        }

        stage ('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
        }

        stage ('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage ('Building docker image') {
            steps {
                sh "sudo docker build -t $DOCKER_IMAGE ."
            }
        }

        stage ('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    sh '''
                    echo "$DOCKERHUB_PASS" | sudo docker login -u "$DOCKERHUB_USER" --password-stdin
                    sudo docker push $DOCKER_IMAGE
                    sudo docker logout
                    '''
                }
            }
        }

    }

}