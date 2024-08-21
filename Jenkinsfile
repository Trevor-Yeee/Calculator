pipeline {
    agent any

    tools {
        jdk 'JDK-21'
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
        stage('Deploy') {
    steps {
        script {
            if (fileExists('target/Calculator-1.0-SNAPSHOT.jar')) {
                // Run the JAR file in the background
                bat 'start java -jar target/Calculator-1.0-SNAPSHOT.jar'
                echo 'JAR file started in the background.'
                
                // Optionally, add a delay to ensure the JAR starts correctly
                sleep(time: 30, unit: 'SECONDS')

                // Optionally, stop the process if needed (e.g., if it's a long-running process)
                bat 'taskkill /F /IM java.exe'
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
