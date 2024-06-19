pipeline {
    agent {
        label 'your-agent-label' // Replace with your Jenkins agent label
    }

    environment {
        FRONTEND_SERVER = 'your-frontend-server'
        BACKEND_SERVER = 'your-backend-server'
        FRONTEND_APP_DIR = '/var/www/frontend'
        BACKEND_APP_DIR = '/var/www/backend'
        FRONTEND_BACKUP_DIR = '/var/backups/frontend'
        BACKEND_BACKUP_DIR = '/var/backups/backend'
        BACKEND_JAR_NAME = 'your-backend-app.jar'
    }

    stages {
        stage('Deploy Frontend') {
            steps {
                script {
                    deployFrontendApp(
                        FRONTEND_SERVER,
                        FRONTEND_APP_DIR,
                        FRONTEND_BACKUP_DIR
                    )
                }
            }
        }

        stage('Deploy Backend') {
            steps {
                script {
                    deployBackendApp(
                        BACKEND_SERVER,
                        BACKEND_APP_DIR,
                        BACKEND_BACKUP_DIR,
                        BACKEND_JAR_NAME
                    )
                }
            }
        }
    }

    // Send email after deploy script finish.
    post {
        success {
            script {
                mail(
                    to: 'success@example.com',
                    subject: "Deployment Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <html>
                    <body>
                        <h2 style="color: green;">Deployment Successful</h2>
                        <p>The deployment was successful.</p>
                        <ul>
                            <li><strong>Job:</strong> ${env.JOB_NAME}</li>
                            <li><strong>Build Number:</strong> ${env.BUILD_NUMBER}</li>
                        </ul>
                        <p>Check the deployment logs for more details.</p>
                    </body>
                    </html>
                    """,
                    mimeType: 'text/html'
                )
            }
        }
        failure {
            script {
                mail(
                    to: 'failure@example.com',
                    subject: "Deployment Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <html>
                    <body>
                        <h2 style="color: red;">Deployment Failed</h2>
                        <p>The deployment failed.</p>
                        <ul>
                            <li><strong>Job:</strong> ${env.JOB_NAME}</li>
                            <li><strong>Build Number:</strong> ${env.BUILD_NUMBER}</li>
                        </ul>
                        <p>Check the deployment logs for more details.</p>
                    </body>
                    </html>
                    """,
                    mimeType: 'text/html'
                )
            }
        }
    }
}

// Functioni definition place at bottom.
def deployFrontendApp(server, appDir, backupDir) {
    sh """
    ssh ${server} 'mkdir -p ${backupDir}'
    // ssh to the server and backup code to date time folder.
    ssh ${server} 'cp -r ${appDir} ${backupDir}/\$(date +%F-%T)'
    ssh ${server} 'rm -rf ${appDir}/*'
    scp -r dist/* ${server}:${appDir}/
    """
}

def deployBackendApp(server, appDir, backupDir, jarName) {
    sh """
    ssh ${server} 'mkdir -p ${backupDir}'
    ssh ${server} 'cp -r ${appDir} ${backupDir}/\$(date +%F-%T)'
    // Find the service name to kill. ('true' is for avoiding failure if process not exist)
    ssh ${server} 'pkill -f ${jarName} || true'
    ssh ${server} 'rm -rf ${appDir}/*'
    scp -r target/${jarName} ${server}:${appDir}/
    ssh ${server} 'nohup java -jar ${appDir}/${jarName} &'
    """
}
