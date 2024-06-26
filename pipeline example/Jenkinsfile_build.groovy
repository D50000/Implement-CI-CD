pipeline {
    agent {
        label 'your-agent-label' // Replace with your Jenkins agent label
    }

    environment {
        GITHUB_CREDENTIALS = credentials('github-credentials-id') // Replace with your GitHub credentials ID in Jenkins
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'The branch to build') // Free inpust text box.
        choice(name: 'ENVIRONMENT', defaultValue: 'sit', choices: ['sit', 'uat', 'prod'], description: 'Build environment') // Drop down option.
    }

    // Pipeline work flows
    stages {
        stage('[1/5] Checkout') {
            steps {
                script {
                    // Checkout the source code from GitHub
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "*/${params.BRANCH_NAME}"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        submoduleCfg: [],
                        userRemoteConfigs: [[
                            credentialsId: GITHUB_CREDENTIALS,
                            url: 'https://github.com/your-repo-url.git' // Replace with your GitHub repository URL
                        ]]
                    ])
                }
            }
        }

        stage('[2/5] Build Frontend') {
            steps {
                script {
                    dir('frontend') {
                        // Build frontend (assumes npm is used)
                        sh 'npm install'
                        sh 'npm run build'
                    }
                }
            }
        }

        stage('[3/5] Build Backend') {
            steps {
                script {
                    dir('backend') {
                        // Build backend (assumes Maven is used)
                        sh 'mvn clean install'
                    }
                }
            }
        }

        stage('[4/5] Run The Test') {
            steps {
                script {
                    // Import and load the test pipeline script
                    load 'jenkins/Jenkinsfile_test.groovy'
                }
            }
        }

        stage('[5/5] Push to Artifact Repo') {
            steps {
                script {
                    // Assuming you have already cloned the artifact repository
                    dir('artifact-repo') {
                        // Multiple sh script command
                        sh """
                        git checkout -b ${params.ENVIRONMENT}-${params.BRANCH_NAME}
                        cp -r ../frontend/dist ./frontend-dist
                        cp -r ../backend/target ./backend-target
                        git add .
                        git commit -m "Adding built artifacts for ${params.ENVIRONMENT}"
                        git push origin ${params.ENVIRONMENT}-${params.BRANCH_NAME}
                        """
                    }
                }
            }
        }
    }

    // Whatever the each stage work or not, it finally will run post
    post {
        success {
            script {
                emailext(
                    to: 'success@example.com',
                    subject: "Build Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <html>
                    <body>
                        <h2 style="color: green;">Build Successful</h2>
                        <p>The build was successful.</p>
                        <ul>
                            <li><strong>Job:</strong> ${env.JOB_NAME}</li>
                            <li><strong>Build Number:</strong> ${env.BUILD_NUMBER}</li>
                            <li><strong>Branch:</strong> ${params.BRANCH_NAME}</li>
                            <li><strong>Environment:</strong> ${params.ENVIRONMENT}</li>
                        </ul>
                        <p>Check the build logs for more details.</p>
                    </body>
                    </html>
                    """,
                    mimeType: 'text/html'
                )
            }
        }
        failure {
            script {
                emailext(
                    to: 'failure@example.com',
                    subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <html>
                    <body>
                        <h2 style="color: red;">Build Failed</h2>
                        <p>The build failed.</p>
                        <ul>
                            <li><strong>Job:</strong> ${env.JOB_NAME}</li>
                            <li><strong>Build Number:</strong> ${env.BUILD_NUMBER}</li>
                            <li><strong>Branch:</strong> ${params.BRANCH_NAME}</li>
                            <li><strong>Environment:</strong> ${params.ENVIRONMENT}</li>
                        </ul>
                        <p>Check the build logs for more details.</p>
                    </body>
                    </html>
                    """,
                    mimeType: 'text/html'
                )
            }
        }
    }
}
