pipeline {
    agent any

    tools {
        jdk 'JDK-11' // Make sure this matches the JDK name configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Trevor-Yeee/Calculator.git'
            }
        }
        stage('Build') {
            steps {
                powershell 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                powershell 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    if (fileExists('target/Calculator-1.0-SNAPSHOT.jar')) {
                        powershell 'java -jar target/Calculator-1.0-SNAPSHOT.jar'
                    } else {
                        error "JAR file not found!"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace'
            deleteDir() // Clean up the workspace after the build
        }
        success {
            echo 'Build succeeded!!!'
            // You could add notification steps here
        }
        failure {
            echo 'Build failed!'
            // You could add notification steps here
        }
    }
}
