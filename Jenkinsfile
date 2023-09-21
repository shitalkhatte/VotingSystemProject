pipeline {
    agent {
      label 'windows'
    }
  
    stages {
        stage('Checkout') {
            steps {
              checkout scm
            }
        }
      stage('Build") {
            steps {
                bat "gradle clean test"
            }
        }
      stage('Deploy')
            {
              steps {
                bat 'echo Deploying on windows'
              }
            }
            }
            post {
              success {
              }
              failure {
              }
            }
            }
