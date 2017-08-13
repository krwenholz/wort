aws lambda create-function \
    --function-name=lambdaCopyTest \
    --role=arn:aws:iam::940600886517:role/service-role/wort.hello.lambda \
    --handler=wort.core::handler \
    --zip-file fileb://~/wort/functions/target/uberjar/wort-0.1.0-SNAPSHOT-standalone.jar \
    --runtime=java8 \
    --region=us-east-1
