node('arm-slave') {
    stage('Checkout') {

        checkout([$class                           : 'GitSCM', branches: [[name: '*/master']],
                  doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
                  userRemoteConfigs                : [[credentialsId: 'f85745ef-053f-453b-aba0-4c014631665d',
                                                       url          : 'https://fsamir@github.com/inventmarine/watchtower.git'
                                                      ]]])

    }

    def dockerImage

    stage('Docker build') {
        dockerImage = docker.build('inventmarine/watchtower:latest')
    }

    //TODO: This is overwritting ~/.docker/config.json and messing with ecr-credentials-help
    //stage('ECR authenticate') {
    ////   sh 'rm -f ~/.docker/config.json || true'
    //     sh 'eval $(aws ecr get-login --region us-east-1)'
    //}

    stage('Docker push') {
        docker.withRegistry('https://710895077843.dkr.ecr.us-east-1.amazonaws.com/inventmarine/watchtower') {
            dockerImage.push('latest')
        }
    }
}
