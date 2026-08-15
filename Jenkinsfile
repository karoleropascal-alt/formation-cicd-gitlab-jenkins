pipeline {
    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    environment {
        GITLAB_REPO = 'http://host.docker.internal/perso/monpremierpointgitlab-cipointyml.git'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: "${GITLAB_REPO}",
                    credentialsId: 'gitlab-local-creds'
            }
        }
        stage('Build') {
            steps {
                dir('mon-app') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Test') {
            steps {
                dir('mon-app') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'mon-app/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                dir('mon-app') {
                    sh 'mvn package -DskipTests'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'mon-app/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Deploy') {
            steps {
                dir('mon-app') {
                    echo 'Étape Deploy à définir selon ta cible'
                }
            }
        }
    }
}



