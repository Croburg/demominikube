if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=mmlinac
fi;
kubectl run apache --image=docker.io/$DOCKER_ACCOUNT/apache:1 --port=80
kubectl expose deployment/apache --type=NodePort
kubectl run checkout-service --image=docker.io/$DOCKER_ACCOUNT/checkout-service:latest --port=8080
kubectl expose deployment/checkout-service --type=NodePort