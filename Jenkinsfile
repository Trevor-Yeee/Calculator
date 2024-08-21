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
                    // Ensure Xvfb is running before deploying
                    sh '''
                        # Check if Xvfb is running
                        if pgrep -x "Xvfb" > /dev/null
                        then
                            echo "Xvfb is running"
                        else
                            echo "Xvfb is not running, starting Xvfb..."
                            Xvfb :99 -screen 0 1024x768x24 &
                        fi

                        # Deploy the JAR file
                        if [ -f target/Calculator-1.0-SNAPSHOT.jar ]; then
                            java -jar target/Calculator-1.0-SNAPSHOT.jar
                        else
                            echo "JAR file not found!"
                            exit 1
                        fi
                    '''
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
