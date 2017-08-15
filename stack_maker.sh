aws cloudformation create-stack \
    --stack-name=LambdaCopierStack \
    --template-body "$(cat pipeline-definition.yaml)" \
    --capabilities=CAPABILITY_IAM \
    --region=us-east-1 --parameters \
    ParameterKey=GitHubUser,ParameterValue=$1 \
    ParameterKey=GitHubToken,ParameterValue=$2 \
    ParameterKey=Repo,ParameterValue=$3 \
    ParameterKey=Branch,ParameterValue=$4
