pipeline {
  
    agent any
    
    tools { 
        maven 'Maven 3' 
        jdk 'jdk8' 
    }

    stages {

        stage('Test-RBAC-Basic') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test,rbac-basic'
            }
        }

//         stage('Test-RBAC-Basic-Cache') {
//             steps {
//                 sh 'mvn test -Dspring.profiles.active=test,rbac-basic-cache'
//             }
//         }

        stage('Test-RBAC-Join') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test,rbac-join'
            }
        }
      
        stage('Build') {
            steps {
                sh 'mvn clean package -Dspring.profiles.active=test'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }

    }

}