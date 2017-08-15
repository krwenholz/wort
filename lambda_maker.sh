aws lambda create-function \
    --function-name=S3CopyLambda \
    --role=arn:aws:iam::940600886517:role/service-role/wort.hello.lambda \
    --handler=lambda.handler \
    --zip-file fileb://~/wort/functions/lambda.zip \
    --runtime=nodejs6.10 \
    --region=us-east-1
