pipeline {
    agent any

    tools {
        jdk 'JDK-21'
    }

    environment {
        DISPLAY = ':99'  // Xvfb display number
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Trevor-Yeee/Calculator.git'
            }
        }
        stage('Build') {
            steps {
                powershell 'mvn package'
            }
        }
        stage('Test') {
            steps {
                powershell 'mvn test'
            }
        }
        stage('Setup Xvfb') {
            steps {
                script {
                    // Start Xvfb for headless operation
                    sh 'Xvfb :99 -screen 0 1024x768x24 &'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    if (fileExists('target/Calculator-1.0-SNAPSHOT.jar')) {
                        // Run the GUI application in the headless environment
                        sh 'java -jar target/Calculator-1.0-SNAPSHOT.jar'
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
            echo 'Build and deployment succeeded!'
            // Add notification steps here if needed
        }
        failure {
            echo 'Build or deployment failed!'
            // Add notification steps here if needed
        }
    }
}
