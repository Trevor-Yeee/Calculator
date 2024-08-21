pipeline { 
    agent any 
    stages { 
        stage('Checkout') { 
            steps { 
                git branch: 'master', url: 'https://github.com/Trevor-Yeee/Calculator.git' 
            } 
        } 
        stage('Build') { 
            steps { powershell 'mvn clean build'} 
        } 
        stage('Test') { 
            steps { powershell 'mvn test'} 
        } 
        stage('Deploy') { 
            steps { powershell 'java -jar target/Calculator-1.0-SNAPSHOT.jar'}            
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
