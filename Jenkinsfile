pipeline {
    agent any
    stages {
        stage('checkout project') {
            steps {
                checkout scm
            }
        }
        stage('test') {
            steps {
                recordIssues enabledForFailure: true, aggregatingResults: true, tool: java()
                sh './mvnw test'
            }
        }
        stage('package') {
            steps {
                sh './mvnw package'
            }
        }
        stage('archive') {
            steps {
                archiveArtifacts 'target/spring-petclinic-*.jar'
            }
        }
    }
}
