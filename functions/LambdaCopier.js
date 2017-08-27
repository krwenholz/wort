exports.handler = function(event, context) {
    var zlib = require('zlib');
    var AWS  = require('aws-sdk');
    var S3   = new AWS.S3();

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

        zlib.gunzip(data.Body, function (err, result) {
            if (err) {
                console.log(err);
                return;
            }

            buckets.out.body = result;

            S3.putObject(buckets.out, function(err, data) {
                if (err) {
                    console.log("Couln't write to S3 bucket " + buckets.out.Bucket);
                    context.fail("Error writing file: " + err);
                    return;
                }

                console.log("Successfully copied frontend files");
                context.succeed();
            });
        });
    });
};
