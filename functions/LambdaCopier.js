exports.handler = function(event, context) {
    var AWS = require('aws-sdk');
    var S3 = new AWS.S3();

    var buckets = {
        in: {
            "Bucket": event.ResourceProperties.S3BucketInput,
            "Key": event.ResourceProperties.S3KeyInput,
        },
        out: {
            "Bucket": event.ResourceProperties.WebsiteBucketName,
            "Key":event.ResourceProperties.S3BucketDestination,
        }
    };

    S3.getObject(buckets.in, function(err, data) {
        if (err) {
            console.log("Couldn't get file " + buckets.in.Key);
            context.fail("Error getting file: " + err);
            return;
        }

        buckets.out.Body = data.Body;

        S3.putObject(buckets.out, function(err, data) {
            if (err) {
                console.log("Couln't write to S3 bucket " + buckets.out.Bucket);
                context.fail("Error writing file: " + err);
            }
            else {
                console.log("Successfully copied " + buckets.in.Key + " to " + buckets.out.Bucket + " at " + buckets.out.Key);
                context.succeed();
            }
        });
    });
};
