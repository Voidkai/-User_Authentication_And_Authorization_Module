pipeline {
  
    agent any
    
    tools { 
        maven 'Maven 3' 
        jdk 'jdk8' 
    }

    stages {
      
        stage('Build') {
            steps {
                sh 'mvn clean package -Dspring.profiles.active='
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }

    }

}