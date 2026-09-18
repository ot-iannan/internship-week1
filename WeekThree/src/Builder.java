// The Product — the final object you’re building (the Request)
class HttpRequest {
    private String url;
    private String method;
    private String body;

    // Only the Builder is allowed to create this
    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.body = builder.body;
    }


    // The Builder — the step-by-step helper that knows how to add each optional piece
    public static class Builder {
        private String url;               // required
        private String method = "GET";    // optional
        private String body = "";         // optional

        // Required part
        public Builder(String url) {
            this.url = url;
        }

        // Optional pieces
        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        // Final step – creates the Product
        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    @Override
    public String toString() {
        return "HttpRequest{url='" + url + "', method='" + method + "', body='" + body + "'}";
    }
}


// The Client — you (or the code) that decides which steps to call
public class Builder {
    public static void main(String[] args) {

        // Simple request
        HttpRequest r1 = new HttpRequest.Builder("https://example.com").build();
        System.out.println(r1);

        // Request with extra pieces
        HttpRequest r2 = new HttpRequest.Builder("https://example.com/api")
                .method("POST")
                .body("{\"name\":\"Alex\"}")
                .build();
        System.out.println(r2);
    }
}

// Note: The Director (optional) is not used in this simple example.
// The Director would be a helper that already knows a few common recipes
// (for example “simple GET request” or “POST with body”).