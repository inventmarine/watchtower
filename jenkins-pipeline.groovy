node('arm-slave') {
    stage('Checkout') {

        checkout([$class                           : 'GitSCM',
                  branches                         : [[name: '*/master']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions                       : [],
                  submoduleCfg                     : [],
                  userRemoteConfigs                : [[credentialsId: 'ae69e313-dd64-4c5b-9bfb-5b3d325fb698',
                                                       url          : 'https://fsamir@github.com/inventmarine/watchtower.git']]])

    }

    def dockerImage

    stage('Docker build') {
        dockerImage = docker.build('inventmarine/watchtower:latest')
    }

    stage('ECR authenticate') {
         sh 'rm -f ~/.docker/config.json || true'
         sh 'eval $(aws ecr get-login --region us-east-1)'
    }

    stage('Docker push') {
        docker.withRegistry('https://710895077843.dkr.ecr.us-east-1.amazonaws.com/inventmarine/watchtower') {
            dockerImage.push('latest')
        }
    }
}
