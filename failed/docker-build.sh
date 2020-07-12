if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=mmlinac
fi;

#docker build --tag=apache apache
#docker tag apache $DOCKER_ACCOUNT/apache:latest
#docker push $DOCKER_ACCOUNT/apache

docker build --tag=checkout-service checkout-service
docker tag checkout-service $DOCKER_ACCOUNT/checkout-service:latest
docker push $DOCKER_ACCOUNT/checkout-service

#docker build --tag=bonuspunkte-service bonuspunkte-service
#docker tag bonuspunkte-service $DOCKER_ACCOUNT/bonuspunkte-service:latest
#docker push $DOCKER_ACCOUNT/bonuspunkte-service

#docker build --tag=orderservice orderservice
#docker tag orderservice $DOCKER_ACCOUNT/orderservice:latest
#docker push $DOCKER_ACCOUNT/orderservice

#docker build --tag=zahlung-service zahlung-service
#docker tag zahlung-service $DOCKER_ACCOUNT/zahlung-service:latest
#docker push $DOCKER_ACCOUNT/zahlung-service

#docker build --tag=versand-service versand-service
#docker tag versand-service $DOCKER_ACCOUNT/versand-service:latest
#docker push $DOCKER_ACCOUNT/versand-service
