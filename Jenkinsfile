pipeline{
    agent any

    stages{
        stage("build"){
            steps{
                sh '''cd dip
                dip provision
                '''
            }
        }
    }
}
