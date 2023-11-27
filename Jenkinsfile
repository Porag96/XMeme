pipeline {
    agent any

    environment {
        // Set your GCP Project ID
        GCP_PROJECT_ID = '	my-dev-project-406406'
        
        // Set your GCP Artifact Registry region
        GCP_REGION = 'us-central1'
        
        // Set the name of your Artifact Registry repository
        ARTIFACT_REGISTRY_REPOSITORY = 'my-image-repo'
        
        // Set the Docker image name and tag
        DOCKER_IMAGE_NAME = 'test'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                // Check out your source code from version control (e.g., Git)
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image using a Dockerfile in your source code
                script {
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push to GCP Artifact Registry') {
            steps {
                // Authenticate with GCP using a service account key
                withCredentials([gcpServiceAccount(project: GCP_PROJECT_ID, credentialsId: 'your-service-account-credentials-id')]) {
                    // Authenticate Docker with GCP
                    sh "gcloud auth configure-docker --quiet"
                    
                    // Tag the Docker image with the GCP Artifact Registry URL
                    sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} gcr.io/${GCP_PROJECT_ID}/${ARTIFACT_REGISTRY_REPOSITORY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                    
                    // Push the Docker image to GCP Artifact Registry
                    sh "docker push gcr.io/${GCP_PROJECT_ID}/${ARTIFACT_REGISTRY_REPOSITORY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }
    }
}

