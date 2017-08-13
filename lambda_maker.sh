aws lambda create-function \
    --function-name=lambdaCopyTest \
    --role=arn:aws:iam::940600886517:role/service-role/wort.hello.lambda \
    --handler=lambda.handler \
    --zip-file fileb://~/wort/lambda.zip \
    --runtime=java8 \
    --region=us-east-1
