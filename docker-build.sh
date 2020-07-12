if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=mmlinac
fi;
docker build --tag=apache apache
docker tag apache $DOCKER_ACCOUNT/apache:latest
docker push $DOCKER_ACCOUNT/apache

docker build --tag=kube-checkout-service checkout-service
docker tag kube-checkout-service $DOCKER_ACCOUNT/kube-checkout-service:latest
docker push $DOCKER_ACCOUNT/kube-checkout-service

docker build --tag=kube-bonuspunkte-service bonuspunkte-service
docker tag kube-bonuspunkte-service $DOCKER_ACCOUNT/kube-bonuspunkte-service:latest
docker push $DOCKER_ACCOUNT/kube-bonuspunkte-service

docker build --tag=kube-orderservice orderservice
docker tag kube-orderservice $DOCKER_ACCOUNT/kube-orderservice:latest
docker push $DOCKER_ACCOUNT/kube-orderservice

docker build --tag=kube-usertask usertask
docker tag kube-usertask $DOCKER_ACCOUNT/kube-usertask:latest
docker push $DOCKER_ACCOUNT/kube-usertask

docker build --tag=kube-versand-service versand-service
docker tag kube-versand-service $DOCKER_ACCOUNT/kube-versand-service:latest
docker push $DOCKER_ACCOUNT/kube-versand-service

docker build --tag=kube-zahlung-service zahlung-service
docker tag kube-zahlung-service $DOCKER_ACCOUNT/kube-zahlung-service:latest
docker push $DOCKER_ACCOUNT/kube-zahlung-service