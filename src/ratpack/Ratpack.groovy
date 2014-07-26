import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        handler("subscriptions") {
            byMethod {
                get {
                    def cid = request.queryParams['cid']
                    if (cid) {
                        render "Got CID ${cid}"
                    } else {
                        response.status 422
                        response.send("application/json", /{"error": "Must set cid"}/)
                    }
                }
                post {
                    //TODO create a subscription
                    render "POST new subscription"
                }
            }
        }

        handler("subscriptions/:id") {
            def id = pathTokens['id']
            if (!id) {
                response.status 422
                response.send()
            }
            byMethod {
                get {
                    //TODO display subscription
                    render "GET $id"
                }
                put {
                    //TODO update subscription
                    render "PUT $id"
                }
            }
        }
    }
}
