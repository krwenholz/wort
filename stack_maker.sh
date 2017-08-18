aws cloudformation create-stack \
    --stack-name=$1 \
    --template-body "$(cat pipeline-definition.yaml)" \
    --capabilities=CAPABILITY_IAM \
    --region=us-east-1 --parameters \
    ParameterKey=GitHubUser,ParameterValue=$2 \
    ParameterKey=GitHubToken,ParameterValue=$3 \
    ParameterKey=Repo,ParameterValue=$4 \
    ParameterKey=Branch,ParameterValue=$5
