pipeline {
  
    agent any
    
    tools { 
        maven 'Maven 3' 
        jdk 'jdk8' 
    }

    stages {
      
        stage('Build') {
            steps {
                sh 'mvn clean packge'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }

    }

}