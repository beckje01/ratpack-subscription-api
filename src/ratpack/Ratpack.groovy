import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        handler {
            //A very simple handler to check token auth on a header
            if (request.headers['Authorization'] != "Token faketoken") {
                response.status(401)
                //We must send some response or the request will hang.
                response.send()
            } else {
                //We can choose to do nothing but allow the next handler in the chain to deal with the request.
                next()
            }
        }
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
