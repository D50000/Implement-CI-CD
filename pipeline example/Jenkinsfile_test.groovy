// Run front-end test.
stage('Test Frontend') {
    dir('frontend') {
        // node example
        sh 'npm test'
    }
}

// Run back-end test.
stage('Test Backend') {
    dir('backend') {
        // java exampe
        sh 'mvn test'
    }
}
