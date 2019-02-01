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
